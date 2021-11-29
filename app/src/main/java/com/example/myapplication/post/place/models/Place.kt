package com.example.myapplication.post.place.models

data class Place(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)