package com.example.myapplication.model

interface ValidationResult {
    companion object {
        val SUCCESS = ValidationSuccess()
    }
}

open class ValidationSuccess : ValidationResult

class ValidationSuccessCode(
    val code: Int
) : ValidationSuccess()

class ValidationErrorCode(
    val code: Int
) : ValidationResult
