package com.example.myapplication.comment.models

data class Comments(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)