package pw.idrug.connections.dialog

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import pw.idrug.connections.R

class CodeInputDialogFragment(
    private val onCodeEntered: (String) -> Unit
) : BottomSheetDialogFragment() {

    private lateinit var inputs: List<EditText>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.dialog_code_input, container, false)
        inputs = listOf(
            v.findViewById(R.id.code_1),
            v.findViewById(R.id.code_2),
            v.findViewById(R.id.code_3),
            v.findViewById(R.id.code_4),
            v.findViewById(R.id.code_5),
            v.findViewById(R.id.code_6)
        )
        for ((i, et) in inputs.withIndex()) {
            et.filters = arrayOf(InputFilter.LengthFilter(1))
            et.inputType = EditorInfo.TYPE_CLASS_NUMBER
            et.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (!s.isNullOrEmpty() && i < inputs.size - 1) {
                        inputs[i + 1].requestFocus()
                    }
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
            et.setOnKeyListener { _, keyCode, _ ->
                if (keyCode == android.view.KeyEvent.KEYCODE_DEL && et.text.isEmpty() && i > 0) {
                    inputs[i - 1].requestFocus()
                    inputs[i - 1].text.clear()
                    true
                } else false
            }
        }
        v.findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            val code = inputs.joinToString(separator = "") { it.text.toString() }
            if (code.length == 6) {
                onCodeEntered(code)
                dismiss()
            } else {
                Toast.makeText(requireContext(), getString(R.string.enter_code), Toast.LENGTH_SHORT).show()
            }
        }
        return v
    }
}
