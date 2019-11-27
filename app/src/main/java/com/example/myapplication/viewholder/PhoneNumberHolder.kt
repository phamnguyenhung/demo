package com.example.myapplication.viewholder

import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.model.PhoneComponent
import com.example.myapplication.model.ViewComponent
import kotlinx.android.synthetic.main.item_view_phone_number_edt.view.*

open class PhoneNumberHolder(
    parent: ViewGroup,
    layoutId: Int = R.layout.item_view_phone_number_edt
) : FormViewHolder(parent, layoutId) {
    override fun onBind(component: ViewComponent) {
        if (component is PhoneComponent) {
            itemView.apply {
                tvTitle.text = component.title
                edtPhone.hint = component.name
            }
        }
    }
}

class NotePhoneNumberHolder(parent: ViewGroup) :
    PhoneNumberHolder(parent, R.layout.item_view_phone_number_edt)

class PlainPhoneNumberHolder(parent: ViewGroup) :
    PhoneNumberHolder(parent, R.layout.item_view_plain_edt)

class SessionPhoneNumberHolder(parent: ViewGroup):
    PhoneNumberHolder(parent, R.layout.item_view_session_name)