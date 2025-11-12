package pw.idrug.connections.dialog

  import android.app.Dialog
  import android.content.Context
  import android.content.Intent
  import android.net.Uri
  import android.os.Bundle
  import android.text.SpannableString
  import android.text.SpannableStringBuilder
  import android.text.Spanned
  import android.text.style.StrikethroughSpan
  import android.text.style.StyleSpan
  import android.view.LayoutInflater
  import android.view.View
  import android.widget.AdapterView
  import android.widget.ArrayAdapter
  import android.widget.Spinner
  import android.widget.TextView
  import android.widget.Toast
  import androidx.core.text.HtmlCompat
  import androidx.fragment.app.DialogFragment
  import androidx.lifecycle.lifecycleScope
  import com.google.android.material.dialog.MaterialAlertDialogBuilder
  import com.google.android.material.textfield.TextInputEditText
  import com.google.android.material.textfield.TextInputLayout
  import java.io.IOException
  import java.text.NumberFormat
  import java.util.LinkedHashSet
  import java.util.Locale
  import kotlin.math.max
  import kotlinx.coroutines.Dispatchers
  import kotlinx.coroutines.launch
  import kotlinx.coroutines.withContext
  import okhttp3.Call
  import okhttp3.Callback
  import okhttp3.FormBody
  import okhttp3.OkHttpClient
  import okhttp3.Request
  import okhttp3.Response
  import org.json.JSONObject
  import pw.idrug.connections.R
  import pw.idrug.connections.catalog.CatalogData
  import pw.idrug.connections.catalog.CatalogDuration
  import pw.idrug.connections.catalog.CatalogLocation
  import pw.idrug.connections.catalog.CatalogRepository
  import pw.idrug.connections.catalog.CatalogTariff

  class SubscriptionDialogFragment : DialogFragment() {

      private lateinit var locationSpinner: Spinner
      private lateinit var durationSpinner: Spinner
      private lateinit var priceText: TextView
      private lateinit var payButton: View
      private lateinit var promoRow: View
      private lateinit var promoContainer: TextInputLayout
      private lateinit var promoInput: TextInputEditText
      private lateinit var promoApplyBtn: View

      private var appliedPromo: String? = null
      private var appliedDiscount: AppliedDiscount? = null

      private val client = OkHttpClient()

      private var catalogData: CatalogData? = null
      private var purchaseOptions: List<PurchaseOption> = emptyList()
      private var locationOptions: List<LocationOption> = emptyList()
      private var durationAdapter: ArrayAdapter<String>? = null
      private var locationAdapter: ArrayAdapter<String>? = null

      private data class PurchaseOption(
          val tariffId: String,
          val displayName: String,
          val priceRub: Int?,
          val priceText: CharSequence?,
          val promoText: CharSequence?,
          val availableLocationIds: List<String>,
          val purchaseDisabled: Boolean,
          val durationDays: Int
      )

      private data class LocationOption(
          val id: String,
          val title: String
      )

      private data class AppliedDiscount(val type: DiscountType, val value: Int)

      private enum class DiscountType { PERCENT, ABSOLUTE }

      override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
          val ctx = requireContext()
          val view = LayoutInflater.from(ctx).inflate(R.layout.dialog_subscription, null)
          locationSpinner = view.findViewById(R.id.spinner_location)
          durationSpinner = view.findViewById(R.id.spinner_duration)
          priceText = view.findViewById(R.id.text_price)
          payButton = view.findViewById(R.id.pay_button)
          promoRow = view.findViewById(R.id.row_promo)
          promoContainer = view.findViewById(R.id.promo_container)
          promoInput = view.findViewById(R.id.input_promo)
          promoApplyBtn = view.findViewById(R.id.btn_apply_promo)

          durationAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_item, mutableListOf())
          durationAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
          durationSpinner.adapter = durationAdapter

          locationAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_item, mutableListOf())
          locationAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
          locationSpinner.adapter = locationAdapter

          durationSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
              override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                  handlePlanSelection(position)
              }

              override fun onNothingSelected(parent: AdapterView<*>) {
                  handlePlanSelection(-1)
              }
          }

          locationSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
              override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                  updatePurchaseAvailability()
              }

              override fun onNothingSelected(parent: AdapterView<*>) {
                  updatePurchaseAvailability()
              }
          }

          promoApplyBtn.setOnClickListener { checkAndApplyPromo() }
          payButton.setOnClickListener { sendPaymentRequest() }

          return MaterialAlertDialogBuilder(ctx, R.style.MonetAlertDialog)
              .setView(view)
              .create()
      }

      override fun onResume() {
          super.onResume()
          loadCatalog()
      }

      private fun loadCatalog() {
          priceText.text = getString(R.string.subscription_catalog_loading)
          payButton.isEnabled = false
          durationSpinner.isEnabled = false
          locationSpinner.isEnabled = false
          setPromoVisibility(false)

          lifecycleScope.launch {
              val data = withContext(Dispatchers.IO) { CatalogRepository.getCatalog(requireContext()) }
              if (!isAdded) return@launch
              if (data == null) {
                  priceText.text = getString(R.string.subscription_catalog_error)
                  Toast.makeText(requireContext(), R.string.subscription_catalog_error, Toast.LENGTH_SHORT).show()
                  purchaseOptions = emptyList()
                  updateLocationAdapter(emptyList())
                  updatePurchaseAvailability()
                  clearPromoState()
              } else {
                  catalogData = data
                  applyCatalog(data)
              }
          }
      }

      private fun applyCatalog(catalog: CatalogData) {
          val locale = currentLocale()
          val activeTariffs = catalog.tariffs.filter { it.visible && it.active && !it.comingSoon && !it.purchaseDisabled }
          if (activeTariffs.isEmpty()) {
              priceText.text = getString(R.string.subscription_catalog_empty)
              purchaseOptions = emptyList()
              updateLocationAdapter(emptyList())
              updatePurchaseAvailability()
              clearPromoState()
              setPromoVisibility(false)
              return
          }

          val options = buildPurchaseOptions(activeTariffs, locale)
          if (options.isEmpty()) {
              priceText.text = getString(R.string.subscription_catalog_empty)
              purchaseOptions = emptyList()
              updateLocationAdapter(emptyList())
              updatePurchaseAvailability()
              clearPromoState()
              setPromoVisibility(false)
              return
          }

          purchaseOptions = options
        durationAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options.map { it.displayName })
        durationAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        durationSpinner.adapter = durationAdapter
        val durationsEnabled = options.isNotEmpty()
        durationSpinner.isEnabled = durationsEnabled
        durationSpinner.isClickable = durationsEnabled
        durationSpinner.alpha = if (durationsEnabled) 1f else 0.4f
        durationSpinner.setSelection(0, false)
        setPromoVisibility(true)
        handlePlanSelection(0)
      }

    private fun buildPurchaseOptions(
        tariffs: List<CatalogTariff>,
        locale: Locale
    ): List<PurchaseOption> {
        val options = mutableListOf<PurchaseOption>()
        for (tariff in tariffs) {
            val baseName = tariff.name?.resolve(locale) ?: tariff.id
            val promoRich = tariff.promo?.resolve(locale)?.let { parseRichText(it) }
            val tariffLocations = tariff.availableLocations
            val durations = tariff.durations
            if (durations.isEmpty()) continue

            durations
                .sortedBy { it.days ?: Int.MAX_VALUE }
                .forEach { duration ->
                    val days = duration.days?.takeIf { it > 0 } ?: return@forEach
                    val displayName = duration.label?.resolve(locale) ?: baseName
                    val priceValue = duration.price
                    val priceHtml = duration.priceText?.resolve(locale)
                    val resolvedPrice = when {
                        !priceHtml.isNullOrBlank() -> parseRichText(priceHtml)
                        priceValue != null -> parseRichText(formatPrice(priceValue))
                        else -> tariff.price?.resolve(locale)?.let { parseRichText(it) }
                    }
                    val locations = if (duration.availableLocations.isNotEmpty()) {
                        duration.availableLocations
                    } else {
                        tariffLocations
                    }
                    options += PurchaseOption(
                        tariffId = tariff.id,
                        displayName = displayName,
                        priceRub = priceValue,
                        priceText = resolvedPrice,
                        promoText = promoRich,
                        availableLocationIds = locations,
                        purchaseDisabled = tariff.purchaseDisabled,
                        durationDays = days
                    )
                }
        }
        return options
    }

      private fun handlePlanSelection(position: Int) {
          if (purchaseOptions.isEmpty()) {
              priceText.text = getString(R.string.subscription_catalog_empty)
              updateLocationAdapter(emptyList())
              updatePurchaseAvailability()
              return
          }
          val safeIndex = position.coerceIn(0, purchaseOptions.lastIndex)
          val option = purchaseOptions.getOrNull(safeIndex) ?: purchaseOptions.first()
          val locations = buildLocationOptions(option)
          updateLocationAdapter(locations)
          updatePrice(option)
          updatePurchaseAvailability()
      }

      private fun updateLocationAdapter(locations: List<LocationOption>) {
          val previousId = locationOptions.getOrNull(locationSpinner.selectedItemPosition)?.id
          locationOptions = locations
        locationAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, locations.map { it.title })
        locationAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        locationSpinner.adapter = locationAdapter
          val targetIndex = locations.indexOfFirst { it.id == previousId }.takeIf { it >= 0 } ?: 0
          if (locations.isNotEmpty()) {
              locationSpinner.setSelection(targetIndex, false)
          }
        val enabled = locations.isNotEmpty()
        locationSpinner.isEnabled = enabled
        locationSpinner.isClickable = enabled
        locationSpinner.alpha = if (enabled) 1f else 0.4f
      }

      private fun updatePrice(option: PurchaseOption) {
          val builder = SpannableStringBuilder()
          val locale = currentLocale()
          val basePrice = option.priceRub
          val discount = appliedDiscount

          if (basePrice != null && basePrice > 0 && discount != null && appliedPromo != null) {
              val discounted = applyDiscount(basePrice, discount)
              val baseSpan = SpannableString(formatPrice(basePrice, locale)).apply {
                  setSpan(StrikethroughSpan(), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
              }
              val discountedSpan = SpannableString(formatPrice(discounted, locale)).apply {
                  setSpan(StyleSpan(android.graphics.Typeface.BOLD), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
              }
              builder.append(baseSpan).append(" ").append(discountedSpan)
          } else {
              when {
                  option.priceText != null -> builder.append(option.priceText)
                  basePrice != null -> builder.append(formatPrice(basePrice, locale))
              }
          }

          option.promoText?.let {
              if (builder.isNotEmpty()) builder.append("\n")
              builder.append(it)
          }

          appliedPromo?.let { code ->
              if (builder.isNotEmpty()) builder.append("\n")
              builder.append(getString(R.string.promo_applied, code))
          }

          priceText.text = builder
      }

      private fun updatePurchaseAvailability() {
          val option = purchaseOptions.getOrNull(durationSpinner.selectedItemPosition)
          val location = locationOptions.getOrNull(locationSpinner.selectedItemPosition)
          val enabled = option != null && location != null && !option.purchaseDisabled && option.durationDays > 0
          payButton.isEnabled = enabled
      }

      private fun buildLocationOptions(option: PurchaseOption): List<LocationOption> {
          val catalog = catalogData ?: return emptyList()
          val locale = currentLocale()
          val ids = LinkedHashSet<String>()
          val source = if (option.availableLocationIds.isNotEmpty()) option.availableLocationIds
          else catalog.locations.filter { it.visible }.map { it.id }
          ids.addAll(source)
          return ids.mapNotNull { id ->
              val meta = catalog.locations.firstOrNull { it.id == id } ?: return@mapNotNull null
              val emoji = meta.emoji.orEmpty()
              val name = meta.getDisplayName(locale) ?: id
              val title = if (emoji.isNotBlank()) "$emoji $name" else name
              LocationOption(id, title)
          }
      }

      private fun currentLocale(): Locale {
          val conf = resources.configuration
          return if (conf.locales.size() > 0) conf.locales[0] else Locale.getDefault()
      }

      private fun parseRichText(value: String): CharSequence =
          HtmlCompat.fromHtml(value, HtmlCompat.FROM_HTML_MODE_LEGACY)

      private fun setPromoVisibility(visible: Boolean) {
          promoRow.visibility = if (visible) View.VISIBLE else View.GONE
      }

      private fun clearPromoState() {
          appliedPromo = null
          appliedDiscount = null
          promoInput.setText("")
      }

      private fun checkAndApplyPromo() {
          val code = promoInput.text?.toString()?.trim().orEmpty()
          if (code.isEmpty()) {
              showToast(getString(R.string.promo_invalid))
              return
          }
          val prefs = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
          val userId = prefs.getString("telegram_id", null).orEmpty()
          if (userId.isEmpty()) {
              showToast(getString(R.string.login_telegram_first))
              return
          }

        val body = FormBody.Builder()
            .add("code", code)
            .add("user_id", userId)
            .build()

        val token = prefs.getString("token", null)
        val requestBuilder = Request.Builder()
            .url("https://idrug.pw/api/check_promocode")
            .post(body)
        if (!token.isNullOrBlank()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }
        val request = requestBuilder.build()

          promoApplyBtn.isEnabled = false
          client.newCall(request).enqueue(object : Callback {
              override fun onFailure(call: Call, e: IOException) {
                  promoApplyBtn.post { promoApplyBtn.isEnabled = true }
                  showToast(getString(R.string.connection_error, e.message ?: ""))
              }

              override fun onResponse(call: Call, response: Response) {
                  promoApplyBtn.post { promoApplyBtn.isEnabled = true }
                  val raw = response.body?.string().orEmpty()
                  if (!response.isSuccessful) {
                      appliedPromo = null
                      appliedDiscount = null
                      purchaseOptions.getOrNull(durationSpinner.selectedItemPosition)?.let { updatePrice(it) }
                      showToast(raw.take(200).ifEmpty { getString(R.string.promo_invalid) })
                      return
                  }

                  val json = runCatching { JSONObject(raw) }.getOrNull()
                  val promo = json?.optJSONObject("promo")
                  val discountJson = promo?.optJSONObject("discount")
                  val type = discountJson?.optString("type").orEmpty().lowercase(Locale.ROOT)
                  val value = discountJson?.optInt("value") ?: 0

                  if (promo?.optBoolean("active") == true && value > 0) {
                      appliedPromo = code
                      appliedDiscount = when (type) {
                          "percent" -> AppliedDiscount(DiscountType.PERCENT, value)
                          "absolute" -> AppliedDiscount(DiscountType.ABSOLUTE, value)
                          else -> null
                      }
                      purchaseOptions.getOrNull(durationSpinner.selectedItemPosition)?.let { option ->
                          activity?.runOnUiThread {
                              updatePrice(option)
                              Toast.makeText(requireContext(), getString(R.string.promo_applied, code), Toast.LENGTH_SHORT).show()
                          }
                      }
                  } else {
                      appliedPromo = null
                      appliedDiscount = null
                      purchaseOptions.getOrNull(durationSpinner.selectedItemPosition)?.let { option ->
                          activity?.runOnUiThread {
                              updatePrice(option)
                              showToast(json?.optString("error") ?: getString(R.string.promo_invalid))
                          }
                      }
                  }
              }
          })
      }

      private fun sendPaymentRequest() {
          val prefs = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
          val userId = prefs.getString("telegram_id", null)
          if (userId.isNullOrBlank()) {
              showToast(getString(R.string.login_telegram_first))
              return
          }

          val option = purchaseOptions.getOrNull(durationSpinner.selectedItemPosition)
          val location = locationOptions.getOrNull(locationSpinner.selectedItemPosition)?.id
          if (option == null || location.isNullOrBlank()) {
              showToast(getString(R.string.subscription_catalog_error))
              return
          }

        val bodyBuilder = FormBody.Builder()
            .add("location", location)
            .add("duration", option.durationDays.toString())
            .add("user_id", userId)

        appliedPromo?.let { bodyBuilder.add("promo_code", it) }

        val jwt = prefs.getString("token", null)
        val requestBuilder = Request.Builder()
            .url("https://idrug.pw/api/pay")
            .post(bodyBuilder.build())
        if (!jwt.isNullOrBlank()) {
            requestBuilder.addHeader("Authorization", "Bearer $jwt")
        }
        val request = requestBuilder.build()

          client.newCall(request).enqueue(object : Callback {
              override fun onFailure(call: Call, e: IOException) {
                  showToast("Network error: ${e.message}")
              }

              override fun onResponse(call: Call, response: Response) {
                  val raw = response.body?.string().orEmpty()
                  if (response.isSuccessful) {
                      val url = runCatching { JSONObject(raw).optString("payment_url") }.getOrDefault("")
                      if (url.isNotEmpty()) {
                          activity?.runOnUiThread { openPaymentPage(url) }
                      } else {
                          showToast(getString(R.string.generic_error))
                      }
                  } else {
                      showToast(raw.take(200).ifEmpty { getString(R.string.generic_error) })
                  }
              }
          })
      }

      private fun openPaymentPage(url: String) {
          try {
              startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
          } catch (e: Exception) {
              showToast("Can't open browser")
          }
      }

      private fun showToast(message: String) {
          activity?.runOnUiThread {
              Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
          }
      }

      private fun formatPrice(amount: Int, locale: Locale = currentLocale()): String {
          val nf = NumberFormat.getIntegerInstance(locale)
          return nf.format(amount) + "\u20BD"
      }

      private fun applyDiscount(base: Int, discount: AppliedDiscount): Int {
          return when (discount.type) {
              DiscountType.PERCENT -> max(1, base * (100 - discount.value) / 100)
              DiscountType.ABSOLUTE -> max(1, base - discount.value)
          }
      }
  }
