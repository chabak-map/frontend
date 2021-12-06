package com.example.myapplication.mypage.profile.models

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Path

interface ProfileRetrofitInterface {
	@Multipart
	@POST("/members/image")
	fun postProfile(@Body param: PostProfileRequest) : Call<ProfileResponse>
}