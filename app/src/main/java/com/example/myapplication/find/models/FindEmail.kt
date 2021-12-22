package com.example.myapplication.find.models

data class FindEmail(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: String
)