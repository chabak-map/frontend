package com.example.myapplication.mypage.profile.models

import retrofit2.Call
import retrofit2.http.*

interface ProfileRetrofitInterface {
	@POST("/members/image")
	fun postProfile(@Body memberImage : PostProfileRequest) : Call<ProfileResponse>

}