package com.example.myapplication.viewholder

import android.view.View
import com.example.myapplication.model.SessionNameComponent
import com.example.myapplication.model.ViewComponent

class SessionNameViewHolder(itemView: View) : FormViewHolder(itemView) {

    override fun onBind(component: ViewComponent) {
        if (component is SessionNameComponent) {
            itemView.apply {

            }
        }
    }
}