package com.example.myapplication.map.detail.models

import com.google.gson.annotations.SerializedName

data class Result(
    val address: String,
    val commentResList: List<CommentRes>,
    val id: Int,
    val imageCount: Int,
    val name: String,
    val phoneNumber: String,
    val placeImageUrls: List<Any>,
    val reviewCount: Int,
    val tagNames: List<Any>
)