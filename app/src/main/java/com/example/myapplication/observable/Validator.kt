package com.example.myapplication.observable

interface ValidatorOwner {
    val validator: Validator<out Any>
    val isValid: Boolean
}

interface Rule<T> {
    fun accept(item: T): Boolean
}

abstract class Validator<T> {
    var error: String? = ""
        private set

    protected abstract val rules: List<Rule<T>>

    fun accept(item: T): Boolean {
        error = rules.find { !it.accept(item) }?.toString()
        return error != null
    }
}

class AllayValid : Validator<Any>() {
    override val rules: List<Rule<Any>> = emptyList()
}