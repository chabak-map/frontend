package com.example.myapplication.post.total.models

data class TotalPost(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)