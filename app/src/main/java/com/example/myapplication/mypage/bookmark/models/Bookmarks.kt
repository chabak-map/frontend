package com.example.myapplication.mypage.bookmark.models

data class Bookmarks(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)