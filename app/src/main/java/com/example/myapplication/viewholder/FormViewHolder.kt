package com.example.myapplication.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.observable.FieldObservable

abstract class FormViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView), Observer<T> {
    private var mItem: T? = null

    constructor(parent: ViewGroup, layoutId: Int) : this(
        LayoutInflater.from(parent.context).inflate(
            layoutId, parent, false
        )
    )

    @Suppress("unchecked_cast")
    @CallSuper
    open fun onBind(component: T) {
        mItem = component
        (component as? FieldObservable<T>)?.subscribe(this)
    }

    override fun onChanged(t: T) {}

    @Suppress("unchecked_cast")
    open fun onRecycled() {
        (mItem as? FieldObservable<T>)?.unsubscribe(this)
        mItem = null
    }
}


