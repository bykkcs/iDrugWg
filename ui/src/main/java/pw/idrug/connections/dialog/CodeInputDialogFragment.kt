package pw.idrug.connections.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import pw.idrug.connections.R

class CodeInputDialogFragment(
    private val onCodeEntered: (String) -> Unit
) : BottomSheetDialogFragment() {

    override fun getTheme(): Int = R.style.CodeInputBottomSheet

    private lateinit var cells: List<TextView>
    private var code: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.bottomsheet_code_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cells = listOf(
            view.findViewById(R.id.pin1),
            view.findViewById(R.id.pin2),
            view.findViewById(R.id.pin3),
            view.findViewById(R.id.pin4),
            view.findViewById(R.id.pin5),
            view.findViewById(R.id.pin6)
        )

        val btns = listOf(
            R.id.btn0,
            R.id.btn1,
            R.id.btn2,
            R.id.btn3,
            R.id.btn4,
            R.id.btn5,
            R.id.btn6,
            R.id.btn7,
            R.id.btn8,
            R.id.btn9,
            R.id.btnDel
        )
        btns.forEach { id ->
            view.findViewById<View>(id).setOnClickListener(this::onDigitClick)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        (dialog as? BottomSheetDialog)?.setCanceledOnTouchOutside(true)
    }

    private fun onDigitClick(v: View) {
        when (v.id) {
            R.id.btnDel -> removeDigit()
            R.id.btn0 -> addDigit("0")
            R.id.btn1 -> addDigit("1")
            R.id.btn2 -> addDigit("2")
            R.id.btn3 -> addDigit("3")
            R.id.btn4 -> addDigit("4")
            R.id.btn5 -> addDigit("5")
            R.id.btn6 -> addDigit("6")
            R.id.btn7 -> addDigit("7")
            R.id.btn8 -> addDigit("8")
            R.id.btn9 -> addDigit("9")
        }
    }

    private fun addDigit(d: String) {
        if (code.length >= 6) return
        cells[code.length].text = d
        code += d
        if (code.length == 6) {
            onCodeEntered(code)
            dismiss()
        }
    }

    private fun removeDigit() {
        if (code.isEmpty()) return
        code = code.dropLast(1)
        cells[code.length].text = ""
    }
}
