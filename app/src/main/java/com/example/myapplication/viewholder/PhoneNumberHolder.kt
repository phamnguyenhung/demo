package com.example.myapplication.viewholder

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemViewCheckErrorEdtBinding
import com.example.myapplication.databinding.ItemViewNoteEdtBinding
import com.example.myapplication.databinding.ItemViewSessionNameBinding
import com.example.myapplication.model.*
import com.example.myapplication.widget.addTextChangedListener

open class PhoneNumberHolder(
        parent: ViewGroup
) : FormViewHolder<PhoneComponent, ItemViewCheckErrorEdtBinding>(
        parent,
        R.layout.item_view_check_error_edt
) {

    init {
        item?.notifyChange()
        viewBinding.edtMain.addTextChangedListener(
                onTextChanged = { text, _, _, _ ->
                    item?.value = text.toString()
                    item?.notifyChange()
                })
    }

    override fun onValid() {
        viewBinding.apply {
            viewTitle.setWarning(null)
            edtMain.setBackgroundState(true)
            tvErrorMsg.visibility = View.GONE
        }
    }

    override fun onError(errorResult: ValidationResult) {
        viewBinding.apply {
            edtMain.setBackgroundState(false)
            if (errorResult is ValidationErrorCode) {
                viewTitle.setWarning("Wrong Format")
                tvErrorMsg.apply {
                    text = itemView.resources.getString(errorResult.getErrorMsg())
                    visibility = View.VISIBLE
                }
            }
        }
    }

    override fun shouldValidate(): Boolean {
        return viewBinding.edtMain.isFocused
    }

    override fun onBind(component: PhoneComponent) {
        viewBinding.apply {
            edtMain.setText(component.value)
            edtMain.setHint(component.hint)
            viewTitle.setTitle(component.title)
            tvErrorMsg.setTextColor(Color.RED)
        }
    }
}