package com.example.myapplication.validator

import com.example.myapplication.observable.Rule
import com.example.myapplication.observable.Validator


// PHONE
class PhoneValidator : Validator<String>() {

    override val rules = arrayListOf(
        RuleLengthAtLeast3(),
        RuleLessThan6()
    )
}

//PLAIN
class PlainValidator : Validator<String>() {

    override val rules = arrayListOf(
        RuleContentNotEmpty()
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

class RuleContentNotEmpty: Rule<String> {

    override fun accept(item: String): Boolean {
        return item.isNotEmpty()
    }

    override fun toString(): String {
        return "content is empty"
    }
}

