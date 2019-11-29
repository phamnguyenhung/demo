package com.example.myapplication.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.ValidationErrorCode
import com.example.myapplication.model.ValidationSuccess
import com.example.myapplication.model.validateErrorCode
import com.example.myapplication.observable.IObservable
import com.example.myapplication.observable.ValidateAble
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
    fun bind(component: T) {
        item = component
        (component as? IObservable<T>)?.subscribe(this)
        onBind(component)
    }

    protected open fun onBind(component: T) {}

    override fun onChanged(item: T) {
        if (shouldValidate() && item is ValidateAble) when (val result = item.validate()) {
            is ValidationSuccess -> onValid()
            is ValidationErrorCode -> validateErrorCode[result.code]
                ?.also { onError(itemView.resources.getString(it)) }
        }
    }

    protected open fun shouldValidate(): Boolean = false

    protected open fun onValid() {

    }

    protected open fun onError(error: String) {

    }

    @Suppress("unchecked_cast")
    open fun onRecycled() {
        (item as? IObservable<T>)?.unsubscribe(this)
        item = null
    }
}


