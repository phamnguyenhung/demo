package com.example.myapplication.model


interface ValidationResult

class ValidationErrorCode(
        val code: String
) : ValidationResult

class ValidationSuccess : ValidationResult