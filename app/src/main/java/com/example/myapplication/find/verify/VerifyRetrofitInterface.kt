package com.example.myapplication.join.verify

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IdVerifyRetrofitInterface {
	@POST("/members/sms/verify")
	fun postVerify(@Body params : PostVerifyRequest) : Call<VerifyResponse>
}