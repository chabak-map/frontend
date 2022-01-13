package com.example.myapplication.map.detail.review.models

import com.google.gson.annotations.SerializedName

data class Result(
    val content: String,
    val name: String,
    val writingDate: String,
    @SerializedName("id") val id : Int,
    @SerializedName("jwt") val jwt: String
)