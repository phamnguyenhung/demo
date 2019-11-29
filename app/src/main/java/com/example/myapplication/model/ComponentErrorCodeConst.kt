package com.example.myapplication.model

import com.example.myapplication.R

const val SMALLER_THAN_3 = "SMALLER_THAN_3"
const val SMALLER_THAN_4 = "SMALLER_THAN_4"

const val GREATER_THAN_3 = "GREATER_THAN_3"
const val GREATER_THAN_4 = "GREATER_THAN_4"
const val GREATER_THAN_5 = "GREATER_THAN_5"
const val GREATER_THAN_6 = "GREATER_THAN_6"

val validateErrorCode = mapOf(
        SMALLER_THAN_3 to R.string.error_length_at_least_3,
        GREATER_THAN_6 to R.string.error_length_should_be_less_than_6
)