package com.example.myapplication.viewholder

import android.view.View
import com.example.myapplication.model.CheckoutButtonComponent
import com.example.myapplication.model.ViewComponent

class CheckoutButtonViewHolder(itemView: View) : FormViewHolder(itemView) {

    override fun onBind(component: ViewComponent) {
        if (component is CheckoutButtonComponent) {
            itemView.apply {

            }
        }
    }
}