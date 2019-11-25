package com.example.myapplication.viewholder

import android.view.View
import com.example.myapplication.model.PlainEdtComponent
import com.example.myapplication.model.ViewComponent

class PlainEdtViewHolder(itemView: View) : FormViewHolder(itemView) {

    override fun onBind(component: ViewComponent) {
        if (component is PlainEdtComponent) {
            itemView.apply {

            }
        }
    }
}