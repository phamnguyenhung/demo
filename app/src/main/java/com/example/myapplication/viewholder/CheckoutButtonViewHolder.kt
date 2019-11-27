package com.example.myapplication.viewholder

import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.model.CheckoutButtonComponent
import com.example.myapplication.model.ViewComponent

class CheckoutButtonViewHolder(parent: ViewGroup) :
    FormViewHolder(parent, R.layout.item_checkout_button) {

    override fun onBind(component: ViewComponent) {
        if (component is CheckoutButtonComponent) {
            itemView.apply {

            }
        }
    }
}