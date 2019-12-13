package com.example.myapplication.validator

import android.util.Patterns
import com.example.myapplication.model.*
import com.example.myapplication.observable.Rule
import com.example.myapplication.observable.Validation


var emailValidRegexPattern = """^[\\w!#${'$'}%&’*+/=?`{|}~^-]+(?:\\.[\\w!#${'$'}%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}${'$'}"""

class EmailValidation : Validation<String>() {

    override fun validate(value: String): ValidationResult {
        val regex = Patterns.EMAIL_ADDRESS.toRegex()
        if (!regex.matches(value)) return ValidationErrorCode(WRONG_FORMAT)
        return super.validate(value)
    }
}

class PhoneValidation : Validation<String>() {

    override fun validate(value: String): ValidationResult {
        if (value.length < 3) return ValidationErrorCode(SMALLER_THAN_3)
        if (value.length >= 6) return ValidationErrorCode(GREATER_THAN_6)
        return super.validate(value)
    }
}

class RuleContentNotEmpty : Rule<String> {

    override fun validate(item: String): ValidationResult {
        if (item.isEmpty()) return ValidationErrorCode(CONTENT_EMPTY)
        return super.validate(item)
    }
}