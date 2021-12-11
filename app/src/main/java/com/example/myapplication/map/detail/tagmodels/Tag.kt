package com.example.myapplication.map.detail.tagmodels

data class Tag(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<String>
)