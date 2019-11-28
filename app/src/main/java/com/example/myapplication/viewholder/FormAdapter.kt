package com.example.myapplication.viewholder

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.*

class DetachAdapter : View.OnAttachStateChangeListener {
    override fun onViewAttachedToWindow(v: View?) {}

    override fun onViewDetachedFromWindow(v: View?) {
        (v as? RecyclerView)?.adapter = null
    }
}

class FormAdapter(view: RecyclerView) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mItems: List<ViewComponent> = emptyList()

    init {
        view.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        view.adapter = this
        view.addOnAttachStateChangeListener(DetachAdapter())
    }

    fun setData(items: List<ViewComponent>) {
        mItems = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewComponent.PHONE_TYPE -> PhoneNumberHolder(parent)
            ViewComponent.NOTE_TYPE -> NoteHolder(parent)
            ViewComponent.PLAIN_TYPE -> PlainEdtHolder(parent)
            ViewComponent.SESSION_NAME_TYPE -> SessionNameHolder(parent)
            ViewComponent.CHECKOUT_BUTTON_TYPE -> CheckoutButtonViewHolder(parent)
            else -> throw Exception("Not support yet!")
        }
    }

    override fun getItemCount() = mItems.size

    @Suppress("unchecked_cast")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? FormViewHolder<ViewComponent>)?.onBind(mItems[position])
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        (holder as? FormViewHolder<*>)?.onRecycled()
    }

    override fun getItemViewType(position: Int) = when (mItems[position]) {
        is PhoneComponent -> ViewComponent.PHONE_TYPE
        is NoteComponent -> ViewComponent.NOTE_TYPE
        is PlainEdtComponent -> ViewComponent.PLAIN_TYPE
        is SessionNameComponent -> ViewComponent.SESSION_NAME_TYPE
        is CheckoutButtonComponent -> ViewComponent.CHECKOUT_BUTTON_TYPE
        else -> error("not support")
    }
}