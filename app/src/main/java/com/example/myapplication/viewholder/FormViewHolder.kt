package com.example.myapplication.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.ViewComponent

abstract class FormViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    constructor(parent: ViewGroup, layoutId: Int) : this(
        LayoutInflater.from(parent.context).inflate(
            layoutId, parent, false
        )
    )

    abstract fun onBind(component: ViewComponent)
    open fun onRecycled() {}
}