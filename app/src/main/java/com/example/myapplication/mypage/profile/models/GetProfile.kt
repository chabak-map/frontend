package com.example.myapplication.mypage.profile.models

data class GetProfile(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)