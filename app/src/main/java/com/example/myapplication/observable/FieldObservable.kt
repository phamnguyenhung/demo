package com.example.myapplication.observable

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import java.util.*

class FieldObservable<T>(default: T) {
    var validator: Validator<T>? = null

    private val mObservers = arrayListOf<Observer<T>>()
    var value: T = default
        set(value) {
            if (value == field) return
            field = value
            notifyChange()
        }

    fun notifyChange() {
        mObservers.forEach { it.onChanged(value) }
    }

    fun subscribe(observer: Observer<T>) {
        if (mObservers.contains(observer)) return
        mObservers.add(observer)
        observer.onChanged(value)
    }

    fun unsubscribe(observer: Observer<T>) {
        mObservers.remove(observer)
    }

    operator fun plus(field: FieldObservable<out Any>): FieldObservable<Any> {
        val next = FieldObservable(Any())
        subscribe(Observer {
            next.value = it as Any
        })
        field.asAnyObservable().subscribe(Observer {
            next.value = it
        })
        return next
    }

    @Suppress("unchecked_cast")
    fun asAnyObservable(): FieldObservable<Any> {
        return this as FieldObservable<Any>
    }

    fun isValid(): Boolean {
        return validator?.accept(this.value) ?: true
    }

}

fun allOf(values: List<FieldObservable<*>>): LiveData<Any> {
    val next = MediatorLiveData<Any>()
    values.forEach {
        it.asAnyObservable().subscribe(Observer {
            next.value = it
        })
    }
    return next
}

interface Validator<T> {
    val error: String
    fun accept(item: T): Boolean
}

private class FieldBinding<T>(
    val observer: Observer<T>,
    val textWatcher: TextWatcher,
    val field: FieldObservable<T>
)

@Suppress("unchecked_cast")
fun EditText.unbind() {
    val binding = tag as? FieldBinding<String> ?: return
    binding.field.unsubscribe(binding.observer)
    removeTextChangedListener(binding.textWatcher)
    tag = null
}

@Suppress("unchecked_cast")
fun EditText.bind(field: FieldObservable<String>) {
    var binding = tag as? FieldBinding<String>
    if (binding == null) {
        binding = FieldBinding(
            Observer {
                if (text.toString() != it) setText(it)
            },
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    field.value = s.toString()
                    if (!field.isValid() && field.validator != null) error = field.validator?.error
                }
            },
            field
        )
        tag = binding
    }
    field.subscribe(binding.observer)
    addTextChangedListener(binding.textWatcher)
}
