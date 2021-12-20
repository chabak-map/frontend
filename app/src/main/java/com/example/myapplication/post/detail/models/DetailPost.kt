package com.example.myapplication.post.detail.models

data class DetailPost(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)