package com.example.myapplication.viewholder

import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemViewNoteEdtBinding
import com.example.myapplication.model.NoteComponent


class NoteHolder(parent: ViewGroup) :
        FormViewHolder<NoteComponent, ItemViewNoteEdtBinding>(
                parent,
                R.layout.item_view_note_edt) {

    init {
        viewBinding.edtMain.addTextChangedListener(
                onTextChanged = { text, _, _, _ ->
                    item?.value = text.toString()
                    item?.notifyChange()
                }
        )
    }

    override fun onBind(component: NoteComponent) {
        viewBinding.apply {
            edtMain.setText(component.value)
            edtMain.setHint(component.hint)
        }
    }
}