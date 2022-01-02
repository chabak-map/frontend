package com.example.myapplication.map.detail.review.write.models

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*

interface WriteCommentRetrofitInterface {

	@POST("/places/{placeId}/comments")
	fun tryPostWriteComment(
		@Body params: WriteCommentRequest, @Path ("placeId") placeId : Int): Call<WriteCommentResponse>
}