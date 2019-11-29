package com.example.myapplication.observable

import androidx.annotation.StringRes
import com.example.myapplication.model.ValidationErrorCode
import com.example.myapplication.model.ValidationResult
import com.example.myapplication.model.ValidationSuccess


interface ValidateAble {
    fun validate(): ValidationResult
    val isValid get() = validate() is ValidationSuccess
}

abstract class Validation<T> : ValidateAble {
    private var function: (() -> T)? = null
    protected open val rules: List<Rule<T>> = emptyList()
    private val value: T get() = function?.invoke() ?: error("Not set value yet!")

    final override fun validate(): ValidationResult {
        for (rule in rules) {
            val result = rule.validate(value)
            if (result is ValidationErrorCode) return result
        }

        return validate(value)
    }

    protected open fun validate(value: T): ValidationResult {
        return ValidationResult.SUCCESS
    }

    fun by(function: () -> T) {
        this.function = function
    }
}

interface Rule<T> {
    fun validate(item: T): ValidationResult {
        return ValidationSuccess()
    }
}

operator fun <T> Rule<T>.plus(rule: Rule<T>): List<Rule<T>> {
    return arrayListOf(this, rule)
}

fun <T> Rule<T>.asList(): List<Rule<T>> {
    return arrayListOf(this)
}

fun Validation<*>.asList(): List<Validation<*>> {
    return arrayListOf(this)
}

interface Validator : ValidateAble {

    val validations: List<Validation<*>>

    override fun validate(): ValidationResult {
        for (validation in validations) {
            val result = validation.validate()
            if (result is ValidationErrorCode) return result
        }
        return ValidationSuccess()
    }

}

class ResourceError(@StringRes val id: Int) : Throwable()

fun throws(text: String): Nothing = throw Throwable(text)
fun throws(@StringRes textId: Int): Nothing = throw ResourceError(textId)