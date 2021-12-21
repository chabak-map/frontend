package com.example.myapplication.post.tag.models

data class PostTag(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<String>
)