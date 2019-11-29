package com.example.myapplication.viewholder

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.myapplication.R
import com.example.myapplication.model.NoteComponent
import com.example.myapplication.model.PhoneComponent
import com.example.myapplication.model.PlainEdtComponent
import com.example.myapplication.model.SessionNameComponent
import kotlinx.android.synthetic.main.item_view_note_edt.*
import kotlinx.android.synthetic.main.item_view_phone_number_edt.*
import kotlinx.android.synthetic.main.item_view_plain_edt.*


open class PhoneNumberHolder(
    parent: ViewGroup,
    layoutId: Int = R.layout.item_view_phone_number_edt
) : FormViewHolder<PhoneComponent>(parent, layoutId) {
    init {
        edtPhone.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            item?.name = text.toString()
            item?.notifyChange()
        })
    }

    override fun onValid() {
        tvPhoneErrorMsg.visibility = View.GONE
    }

    override fun onError(error: String) {
        tvPhoneErrorMsg.text = error
        tvPhoneErrorMsg.visibility = View.VISIBLE
    }

    override fun shouldValidate(): Boolean {
        return edtPhone.isFocused
    }

    override fun onBind(component: PhoneComponent) {
        super.onBind(component)
        tvPhoneTitle.text = component.title
        edtPhone.setText(component.name)
        edtPhone.hint = component.hint
        tvPhoneErrorMsg.setTextColor(Color.RED)
    }
}

class NoteHolder(parent: ViewGroup) :
    FormViewHolder<NoteComponent>(parent, R.layout.item_view_note_edt) {
    override fun onBind(component: NoteComponent) {
        super.onBind(component)
        edtNote.setText(component.name)
        edtNote.hint = component.hint
    }
}

class PlainEdtHolder(parent: ViewGroup) :
    FormViewHolder<PlainEdtComponent>(parent, R.layout.item_view_plain_edt) {

    init {
        edtContent.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            item?.name = text.toString()
            item?.notifyChange()
        })
    }

    override fun onBind(component: PlainEdtComponent) {
        super.onBind(component)
        edtContent.setText(component.name)
        edtContent.hint = component.hint
        tvPlainTitle.text = component.hint
    }

    override fun shouldValidate(): Boolean {
        return edtContent.isFocused
    }

    override fun onValid() {
        tvErrorMsg.visibility = View.GONE
    }

    override fun onError(error: String) {
        tvErrorMsg.visibility = View.VISIBLE
        tvErrorMsg.text = error
    }
}

class SessionNameHolder(parent: ViewGroup) :
    FormViewHolder<SessionNameComponent>(parent, R.layout.item_view_session_name)