package com.example.myapplication.viewholder

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemViewCheckErrorEdtBinding
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
        viewBinding.edtMain.addTextChangedListener(
                onTextChanged = { text, _, _, _ ->
                    item?.name = text.toString()
                    item?.notifyChange()
                })
    }

    override fun onValid() {
        viewBinding.tvErrorMsg.visibility = View.GONE
    }

    override fun onError(errorResult: ValidationResult) {
        if (errorResult is ValidationErrorCode) {
            viewBinding.tvErrorMsg.apply {
                text = itemView.resources.getString(errorResult.getErrorMsg())
                visibility = View.VISIBLE
            }
        }
    }

    override fun shouldValidate(): Boolean {
        return viewBinding.edtMain.isFocused
    }

    override fun onBind(component: PhoneComponent) {
        viewBinding.apply {
            tvTitle.text = component.title
            edtMain.setText(component.name)
            edtMain.setHint(component.hint)
            tvErrorMsg.setTextColor(Color.RED)
        }
    }
}

class NoteHolder(parent: ViewGroup) :
        FormViewHolder<NoteComponent, ItemViewCheckErrorEdtBinding>(
                parent,
                R.layout.item_view_check_error_edt) {

    init {
        viewBinding.edtMain.addTextChangedListener(
                onTextChanged = { text, _, _, _ ->
                    item?.name = text.toString()
                    item?.notifyChange()
                }
        )
    }

    override fun onBind(component: NoteComponent) {
        viewBinding.apply {
            edtMain.setText(component.name)
            edtMain.setHint(component.hint)
        }
    }
}

class PlainEdtHolder(parent: ViewGroup) :
        FormViewHolder<PlainEdtComponent, ItemViewCheckErrorEdtBinding>(
                parent,
                R.layout.item_view_check_error_edt) {

    init {
        viewBinding.edtMain.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            item?.name = text.toString()
            item?.notifyChange()
        })
    }

    override fun onBind(component: PlainEdtComponent) {
        viewBinding.apply {
            edtMain.setText(component.name)
            edtMain.setHint(component.hint)
            tvTitle.text = component.hint
        }
    }

    override fun shouldValidate(): Boolean {
        return viewBinding.edtMain.isFocused
    }

    override fun onValid() {
        viewBinding.tvErrorMsg.visibility = View.GONE
    }

    override fun onError(errorResult: ValidationResult) {
        if (errorResult is ValidationErrorCode) {
            viewBinding.tvErrorMsg.apply {
                visibility = View.VISIBLE
                text = itemView.resources.getString(errorResult.getErrorMsg())
            }
        }
    }
}

class SessionNameHolder(parent: ViewGroup) :
        FormViewHolder<SessionNameComponent, ItemViewSessionNameBinding>(
                parent,
                R.layout.item_view_session_name) {

    override fun onBind(component: SessionNameComponent) {
        viewBinding.tvTitle.text = component.name
    }
}