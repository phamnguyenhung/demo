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
        context.withStyledAttributes(attrs, R.styleable.CheckErrorEditText) {
            viewBinding.edtInput.hint = getString(R.styleable.CheckErrorEditText_hint)
        }
    }

    override fun isFocused(): Boolean {
        return viewBinding.edtInput.isFocused
    }

    fun setText(charSequence: CharSequence) {
        viewBinding.edtInput.setText(charSequence)
    }

    fun setHint(charSequence: CharSequence) {
        viewBinding.edtInput.hint = charSequence
    }

    fun setBackgroundState(isValid: Boolean) {
        val resId = if (isValid) {
            R.drawable.bg_white_border_gray_5dp
        } else {
            R.drawable.bg_white_border_red_5dp
        }
        viewBinding.edtInput.setBackgroundResource(resId)
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
    viewBinding.edtInput.addTextChangedListener(
            beforeTextChanged = beforeTextChanged,
            onTextChanged = onTextChanged,
            afterTextChanged = afterTextChanged
    )
}