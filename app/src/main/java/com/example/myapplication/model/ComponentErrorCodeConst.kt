package com.example.myapplication.model

import com.example.myapplication.R

const val UN_KNOWN = -1
const val SMALLER_THAN_3 = 0
const val SMALLER_THAN_4 = 1

const val GREATER_THAN_3 = 2
const val GREATER_THAN_4 = 3
const val GREATER_THAN_5 = 4
const val GREATER_THAN_6 = 5

const val WRONG_FORMAT = 6
const val CONTENT_EMPTY = 7

val validateErrorCode = mapOf(
        UN_KNOWN to 0,
        SMALLER_THAN_3 to R.string.error_length_at_least_3,
        GREATER_THAN_6 to R.string.error_length_should_be_less_than_6,
        WRONG_FORMAT to R.string.wrong_format,
        CONTENT_EMPTY to R.string.content_is_empty
)

fun ValidationErrorCode.getErrorMsg() = validateErrorCode[this.code] ?: -1

fun ValidationErrorCode.isContentEmpty() = this.code == CONTENT_EMPTY