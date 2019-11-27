package com.example.myapplication.validator

import com.example.myapplication.observable.Rule
import com.example.myapplication.observable.Validator

class PhoneValidator : Validator<String>() {
    override val rules = arrayListOf(
        RuleLengthAtLeast3(),
        RuleLessThan6()
    )
}

class RuleLengthAtLeast3 : Rule<String> {
    override fun accept(item: String): Boolean {
        return item.length > 3
    }

    override fun toString(): String {
        return "Length should great than 3"
    }
}

class RuleLessThan6 : Rule<String> {
    override fun accept(item: String): Boolean {
        return item.length < 6
    }

    override fun toString(): String {
        return "Length should great than 3"
    }
}