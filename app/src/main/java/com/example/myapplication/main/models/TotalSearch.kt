package com.example.myapplication.main.models

data class TotalSearch(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)