package com.example.myapplication.mypage.profile.models

import retrofit2.Call
import retrofit2.http.GET

interface GetProfileRetrofitInterface {
	@GET("/members")
	fun getProfile() : Call<GetProfile>
}