package com.example.myapplication.map.detail.review.write.models

import com.google.gson.annotations.SerializedName

data class WriteComment(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Int,
    @SerializedName("id") val id : Int,
    @SerializedName("jwt") val jwt: String
)