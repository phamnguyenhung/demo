package com.example.myapplication.observable

import androidx.annotation.StringRes


interface ValidateAble {
    fun validate()
}

abstract class Validation<T> : ValidateAble {
    private var function: (() -> T)? = null
    protected open val rules: List<Rule<T>> = emptyList()
    private val value: T get() = function?.invoke() ?: error("Not set value yet!")

    final override fun validate() {
        rules.forEach { it.validate(value) }
        validate(value)
    }

    protected open fun validate(value: T) {
    }

    fun by(function: () -> T) {
        this.function = function
    }
}

interface Rule<T> {
    @Throws
    fun validate(item: T)
}


operator fun <T> Rule<T>.plus(rule: Rule<T>): List<Rule<T>> {
    return arrayListOf(this, rule)
}

fun Validation<*>.asList(): List<Validation<*>> {
    return arrayListOf(this)
}

interface Validator : ValidateAble {

    val validations: List<Validation<*>>

    override fun validate() {
        validations.forEach { it.validate() }
    }

}

class ResourceError(@StringRes val id: Int) : Throwable()

fun throws(text: String): Nothing = throw Throwable(text)
fun throws(@StringRes textId: Int): Nothing = throw ResourceError(textId)