package com.example.myapplication.home.detail

data class Rank(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)