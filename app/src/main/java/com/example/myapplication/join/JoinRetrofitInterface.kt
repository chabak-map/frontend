package com.example.myapplication.join

import com.example.myapplication.join.models.PostSignUpRequest
import com.example.myapplication.join.models.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface JoinRetrofitInterface {

	@POST("/members")
	fun postSignUp(@Body params : PostSignUpRequest) : Call<SignUpResponse>
}