package com.example.myapplication.post.mypost.models

data class MyPost(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)