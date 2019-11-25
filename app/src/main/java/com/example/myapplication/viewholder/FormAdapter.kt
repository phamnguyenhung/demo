package com.example.myapplication.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.ViewComponent
import com.example.myapplication.model.ViewComponent.Companion.CHECKOUT_BUTTON_TYPE
import com.example.myapplication.model.ViewComponent.Companion.NOTE_TYPE
import com.example.myapplication.model.ViewComponent.Companion.PHONE_TYPE
import com.example.myapplication.model.ViewComponent.Companion.PLAIN_TYPE
import com.example.myapplication.model.ViewComponent.Companion.SESSION_NAME_TYPE
import java.lang.Exception

class FormAdapter(
    private val dataList: MutableList<ViewComponent> = mutableListOf()
) : RecyclerView.Adapter<FormViewHolder>() {

    fun setData(dataList: List<ViewComponent>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        return when (viewType) {
            PHONE_TYPE -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_view_phone_number_edt, parent, false)
                PhoneNumberEdtViewHolder(itemView)
            }
            NOTE_TYPE -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_view_phone_number_edt, parent, false)
                PhoneNumberEdtViewHolder(itemView)
            }
            PLAIN_TYPE -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_view_plain_edt, parent, false)
                PhoneNumberEdtViewHolder(itemView)
            }
            SESSION_NAME_TYPE -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_view_session_name, parent, false)
                PhoneNumberEdtViewHolder(itemView)
            }
            CHECKOUT_BUTTON_TYPE -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_checkout_button, parent, false)
                CheckoutButtonViewHolder(itemView)
            }
            else -> {
                throw Exception("wrong type")
            }
        }
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: FormViewHolder, position: Int) {
        dataList.getOrNull(position)?.let {
            holder.onBind(it)
        }
    }

    override fun getItemViewType(position: Int) = dataList[position].viewType
}