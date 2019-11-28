package com.example.myapplication.validator

import com.example.myapplication.R
import com.example.myapplication.observable.Rule
import com.example.myapplication.observable.Validation
import com.example.myapplication.observable.throws


class PhoneValidation : Validation<String>() {
    override fun validate(value: String) {
        if (value.length < 3) throws(R.string.error_length_at_least_3)
        if (value.length >= 6) error("Length should be less than 6")
    }
}

class RuleContentNotEmpty : Rule<String> {
    override fun validate(item: String) {
        if (item.isEmpty()) throws("content is empty")
    }
}

