package com.example.myapplication.map.detail.models

data class MapDetail(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)