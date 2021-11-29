package com.example.myapplication.map.models

data class RadiusPlace(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)