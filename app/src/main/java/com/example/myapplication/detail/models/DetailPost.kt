package com.example.myapplication.detail.models

data class DetailPost(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)