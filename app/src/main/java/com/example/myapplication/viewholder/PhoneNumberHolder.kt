package com.example.myapplication.viewholder

import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.model.PhoneComponent
import com.example.myapplication.model.PlainEdtComponent
import com.example.myapplication.model.SessionNameComponent
import kotlinx.android.synthetic.main.item_view_phone_number_edt.view.*


open class PhoneNumberHolder(
    parent: ViewGroup,
    layoutId: Int = R.layout.item_view_phone_number_edt
) : FormViewHolder<PhoneComponent>(parent, layoutId) {

    override fun onBind(component: PhoneComponent) {
        super.onBind(component)
        itemView.apply {
            tvTitle.text = component.title
            edtPhone.hint = component.name
        }
    }
}

class NoteHolder(parent: ViewGroup) :
    FormViewHolder<PhoneComponent>(parent, R.layout.item_view_phone_number_edt)

class PlainHolder(parent: ViewGroup) :
    FormViewHolder<PlainEdtComponent>(parent, R.layout.item_view_plain_edt)

class SessionNameHolder(parent: ViewGroup) :
    FormViewHolder<SessionNameComponent>(parent, R.layout.item_view_session_name)