package com.example.myapplication.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.ViewComponent

abstract class FormViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(component: ViewComponent)
}