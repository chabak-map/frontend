package com.example.myapplication.detail.models

data class Result(
    val content: String,
    val nickname: String,
    val postImageUrls: List<String>,
    val title: String
)