package com.example.myapplication.map.models

data class ResultX(
    val address: String,
    val commentResList: List<CommentRes>,
    val imageCount: Int,
    val name: String,
    val phoneNumber: String,
    val placeImageUrls: List<String>,
    val reviewCount: Int,
    val tagNames: List<String>
)