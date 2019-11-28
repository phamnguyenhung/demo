package com.example.myapplication.viewholder

import android.graphics.Color
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.myapplication.R
import com.example.myapplication.model.PhoneComponent
import com.example.myapplication.model.PlainEdtComponent
import com.example.myapplication.model.SessionNameComponent
import kotlinx.android.synthetic.main.item_view_phone_number_edt.*
import kotlinx.android.synthetic.main.item_view_plain_edt.*


open class PhoneNumberHolder(
    parent: ViewGroup,
    layoutId: Int = R.layout.item_view_phone_number_edt
) : FormViewHolder<PhoneComponent>(parent, layoutId) {
    init {
        edtPhone.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            item?.name = text.toString()
            item?.notifyChange()
        })
    }

    override fun onBind(component: PhoneComponent) {
        super.onBind(component)
        itemView.apply {
            tvPhoneTitle.text = component.title
            edtPhone.hint = component.name
        }
    }

    override fun onError(error: String) {
        tvPhoneTitle.setTextColor(Color.RED)
    }

    override fun onValid() {
        tvPhoneTitle.setTextColor(Color.BLACK)
    }
}

class NoteHolder(parent: ViewGroup) :
    FormViewHolder<PhoneComponent>(parent, R.layout.item_view_phone_number_edt)

class PlainEdtHolder(parent: ViewGroup) :
    FormViewHolder<PlainEdtComponent>(parent, R.layout.item_view_plain_edt) {

    init {
        edtContent.addTextChangedListener(onTextChanged = { text, start, count, after ->
            item?.name = text.toString()
            item?.notifyChange()
        })
    }

    override fun onBind(component: PlainEdtComponent) {
        super.onBind(component)
        itemView.apply {
            tvTitle.text = component.name
            edtContent.hint = component.name
        }
    }

    override fun onValid() {
        tvErrorMsg.text = null
    }

    override fun onError(error: String) {
        tvErrorMsg.text = error
    }
}

class SessionNameHolder(parent: ViewGroup) :
    FormViewHolder<SessionNameComponent>(parent, R.layout.item_view_session_name)