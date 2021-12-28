package com.example.myapplication.map.detail.review.models

data class MapReview(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)