package com.example.myapplication.post.place.models

data class Result(
    val address: String,
    val imageCount: Int,
    val name: String,
    val phoneNumber: String,
    val placeImageUrls: List<String>,
    val placeReviewResList: List<PlaceReviewRes>,
    val reviewCount: Int,
    val tagNames: List<String>
)