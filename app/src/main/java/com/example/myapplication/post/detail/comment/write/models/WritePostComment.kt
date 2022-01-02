package com.example.myapplication.post.detail.comment.write.models

data class WritePostComment(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Int
)