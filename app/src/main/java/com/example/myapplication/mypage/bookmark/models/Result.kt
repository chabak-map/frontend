package com.example.myapplication.mypage.bookmark.models

data class Result(
    val address: String,
    val imageUrl: String,
    val name: String,
    val placeTagNames: List<String>
)