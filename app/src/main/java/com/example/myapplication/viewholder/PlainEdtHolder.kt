package com.example.myapplication.viewholder

import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemViewCheckErrorEdtBinding
import com.example.myapplication.databinding.ItemViewSessionNameBinding
import com.example.myapplication.model.*
import com.example.myapplication.widget.addTextChangedListener

class PlainEdtHolder(parent: ViewGroup) :
        FormViewHolder<PlainEdtComponent, ItemViewCheckErrorEdtBinding>(
                parent,
                R.layout.item_view_check_error_edt) {

    init {
        viewBinding.edtMain.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            item?.value = text.toString()
            item?.notifyChange()
        })
    }

    override fun onBind(component: PlainEdtComponent) {
        viewBinding.apply {
            edtMain.setText(component.value)
            edtMain.setHint(component.hint)
            viewTitle.setTitle(component.title)
        }
    }

    override fun shouldValidate(): Boolean {
        return viewBinding.edtMain.isFocused
    }

    override fun onValid() {
        viewBinding.apply {
            edtMain.setBackgroundState(true)
            viewTitle.setWarning(null)
            tvErrorMsg.visibility = View.GONE
        }
    }

    override fun onError(errorResult: ValidationResult) {
        viewBinding.apply {
            edtMain.setBackgroundState(false)
            if (errorResult is ValidationErrorCode) {
                viewTitle.setWarning("required")
                tvErrorMsg.apply {
                    visibility = View.VISIBLE
                    text = itemView.resources.getString(errorResult.getErrorMsg())
                }
            }
        }
    }
}