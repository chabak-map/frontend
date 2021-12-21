package com.example.myapplication.post.total.models

data class Result(
    val commentCount: Int,
    val content: String,
    val createdAt: String,
    val id: Int,
    val imageUrls: List<String>,
    val nickname: String,
    val title: String
)