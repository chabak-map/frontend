package com.example.myapplication.post.write.models

data class WritePost(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Int
)