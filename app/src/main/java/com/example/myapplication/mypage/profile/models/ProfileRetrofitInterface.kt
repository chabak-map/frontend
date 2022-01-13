package com.example.myapplication.mypage.profile.models

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ProfileRetrofitInterface {
	@Multipart
	@POST("/members/image")
	fun postProfile(@Part memberImage: MultipartBody.Part) : Call<ProfileResponse>

}