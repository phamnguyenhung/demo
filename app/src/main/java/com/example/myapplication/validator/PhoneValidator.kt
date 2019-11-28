package com.example.myapplication.validator

import com.example.myapplication.model.*
import com.example.myapplication.observable.Rule
import com.example.myapplication.observable.Validation
import com.example.myapplication.observable.throws


class PhoneValidation : Validation<String>() {
    override fun validate(value: String): ValidationResult {
        if (value.length < 3) return ValidationErrorCode(SMALLER_THAN_3)
        if (value.length >= 6) return ValidationErrorCode(GREATER_THAN_6)
        return super.validate(value)
    }
}

class RuleContentNotEmpty : Rule<String> {
    override fun validate(item: String): ValidationResult {
        if (item.isEmpty()) throws("content is empty")
        return super.validate(item)
    }
}