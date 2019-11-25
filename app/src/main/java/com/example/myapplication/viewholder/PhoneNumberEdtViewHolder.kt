package com.example.myapplication.viewholder

import android.view.View
import com.example.myapplication.model.PhoneComponent
import com.example.myapplication.model.ViewComponent
import kotlinx.android.synthetic.main.item_view_phone_number_edt.view.*

class PhoneNumberEdtViewHolder(itemView: View) : FormViewHolder(itemView) {
    override fun onBind(component: ViewComponent) {
        if (component is PhoneComponent) {
            itemView.apply {
                tvTitle.text = component.title
                edtPhone.hint = component.name
            }
        }
    }
}