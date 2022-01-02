package com.example.myapplication.map.detail.review.models

import com.example.myapplication.map.detail.review.write.models.WriteCommentResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MapReviewRetrofitInterface {

	@GET("/places/{placeId}/comments")
	fun getMapReview(@Path("placeId") placeId : Int) : Call<MapReview>
}