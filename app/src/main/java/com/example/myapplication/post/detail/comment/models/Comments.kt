package com.example.myapplication.post.detail.comment.models

import com.example.myapplication.post.detail.comment.models.Result

data class Comments(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)