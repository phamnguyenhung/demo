package com.example.myapplication.widget

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ViewCheckErrorEdtBinding

class CheckErrorEditText @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var viewBinding: ViewCheckErrorEdtBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.view_check_error_edt,
            this,
            true
    )

    init {
        context.withStyledAttributes(attrs, R.styleable.CHeckErrorEdittext) {
            viewBinding.etInput.hint = getString(R.styleable.CHeckErrorEdittext_hint)
        }
    }

    fun setText(charSequence: CharSequence) {
        viewBinding.etInput.setText(charSequence)
    }

    fun setHint(charSequence: CharSequence) {
        viewBinding.etInput.hint = charSequence
    }
}

inline fun CheckErrorEditText.addTextChangedListener(
        crossinline beforeTextChanged: (
                text: CharSequence?,
                start: Int,
                count: Int,
                after: Int
        ) -> Unit = { _, _, _, _ -> },
        crossinline onTextChanged: (
                text: CharSequence?,
                start: Int,
                count: Int,
                after: Int
        ) -> Unit = { _, _, _, _ -> },
        crossinline afterTextChanged: (text: Editable?) -> Unit = {}
) {
    viewBinding.etInput.addTextChangedListener(
            beforeTextChanged = beforeTextChanged,
            onTextChanged = onTextChanged,
            afterTextChanged = afterTextChanged
    )
}