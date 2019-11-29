package com.example.myapplication.model

import com.example.myapplication.R

const val SMALLER_THAN_3 = 0
const val SMALLER_THAN_4 = 1

const val GREATER_THAN_3 = 2
const val GREATER_THAN_4 = 3
const val GREATER_THAN_5 = 4
const val GREATER_THAN_6 = 5

val validateErrorCode = mapOf(
    SMALLER_THAN_3 to R.string.error_length_at_least_3,
    GREATER_THAN_6 to R.string.error_length_should_be_less_than_6
)