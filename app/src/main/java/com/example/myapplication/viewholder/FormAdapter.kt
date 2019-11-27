package com.example.myapplication.viewholder

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.ViewComponent
import com.example.myapplication.model.ViewComponent.Companion.CHECKOUT_BUTTON_TYPE
import com.example.myapplication.model.ViewComponent.Companion.NOTE_TYPE
import com.example.myapplication.model.ViewComponent.Companion.PHONE_TYPE
import com.example.myapplication.model.ViewComponent.Companion.PLAIN_TYPE
import com.example.myapplication.model.ViewComponent.Companion.SESSION_NAME_TYPE

class DetachAdapter : View.OnAttachStateChangeListener {
    override fun onViewAttachedToWindow(v: View?) {}

    override fun onViewDetachedFromWindow(v: View?) {
        (v as? RecyclerView)?.adapter = null
    }
}

class FormAdapter(view: RecyclerView) : RecyclerView.Adapter<FormViewHolder>() {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        return when (viewType) {
            PHONE_TYPE -> PhoneNumberHolder(parent)
            NOTE_TYPE -> NotePhoneNumberHolder(parent)
            PLAIN_TYPE -> PlainPhoneNumberHolder(parent)
            SESSION_NAME_TYPE -> SessionPhoneNumberHolder(parent)
            CHECKOUT_BUTTON_TYPE -> CheckoutButtonViewHolder(parent)
            else -> throw Exception("Not support yet!")
        }
    }

    override fun getItemCount() = mItems.size

    override fun onBindViewHolder(holder: FormViewHolder, position: Int) {
        holder.onBind(mItems[position])
    }

    override fun onViewRecycled(holder: FormViewHolder) {
        super.onViewRecycled(holder)
        holder.onRecycled()
    }

    override fun getItemViewType(position: Int) = mItems[position].viewType
}