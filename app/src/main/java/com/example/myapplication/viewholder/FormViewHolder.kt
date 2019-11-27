package com.example.myapplication.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.observable.FieldObservable
import com.example.myapplication.observable.IObservable
import com.example.myapplication.observable.ValidatorOwner
import kotlinx.android.extensions.LayoutContainer


abstract class FormViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView), Observer<T>,
    LayoutContainer {
    override val containerView: View?
        get() = itemView
    protected var item: T? = null
        private set

    constructor(parent: ViewGroup, layoutId: Int) : this(
        LayoutInflater.from(parent.context).inflate(
            layoutId, parent, false
        )
    )

    @Suppress("unchecked_cast")
    @CallSuper
    open fun onBind(component: T) {
        item = component
        (component as? IObservable<T>)?.subscribe(this)
    }

    @CallSuper
    override fun onChanged(item: T) {
        if (item is ValidatorOwner) {
            val isSuccess = item.isValid
            onValidate(isSuccess, item.validator.error)
        }
    }

    protected open fun onValidate(success: Boolean, error: String?) {
    }

    @Suppress("unchecked_cast")
    open fun onRecycled() {
        (item as? IObservable<T>)?.unsubscribe(this)
        item = null
    }
}


