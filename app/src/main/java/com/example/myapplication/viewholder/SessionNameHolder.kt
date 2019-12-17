package com.example.myapplication.viewholder

import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemViewSessionNameBinding
import com.example.myapplication.model.SessionNameComponent

class SessionNameHolder(parent: ViewGroup) :
        FormViewHolder<SessionNameComponent, ItemViewSessionNameBinding>(
                parent,
                R.layout.item_view_session_name) {

    override fun onBind(component: SessionNameComponent) {
        viewBinding.tvTitle.text = component.name
    }
}