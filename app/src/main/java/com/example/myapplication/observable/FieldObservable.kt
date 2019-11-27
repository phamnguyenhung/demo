package com.example.myapplication.observable

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

interface IObservable<T> {
    fun notifyChange()
    fun subscribe(observer: Observer<T>)
    fun unsubscribe(observer: Observer<T>)
}

interface ObservableOwner {
    val observable: IObservable<Any>
}

abstract class Observable<T> : IObservable<T> {
    @Transient
    private val mObservers = hashSetOf<Observer<T>>()

    override fun subscribe(observer: Observer<T>) {
        mObservers.add(observer)
        notifyChange(observer)
    }

    override fun unsubscribe(observer: Observer<T>) {
        mObservers.remove(observer)
    }

    override fun notifyChange() {
        mObservers.forEach { notifyChange(it) }
    }

    protected abstract fun notifyChange(observer: Observer<T>)

    @Suppress("unchecked_cast")
    fun asAnyObservable(): Observable<Any> {
        return this as Observable<Any>
    }

}

abstract class SelfObservable<T> : Observable<T>(), ObservableOwner {
    override val observable: IObservable<Any>
        get() = asAnyObservable()

    override fun notifyChange(observer: Observer<T>) {
        @Suppress("UNCHECKED_CAST")
        observer.onChanged(this as? T)
    }
}

class ObservableDelegate<T> : Observable<T>() {
    override fun notifyChange(observer: Observer<T>) {
        observer.onChanged(default ?: error("Not setted yet!"))
    }

    var default: T? = null
}

class FieldObservable<T>(default: T) : Observable<T>() {
    var validator: Validator<T>? = null

    var value: T = default
        set(value) {
            if (value == field) return
            field = value
            notifyChange()
        }

    override fun notifyChange(observer: Observer<T>) {
        observer.onChanged(value)
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
