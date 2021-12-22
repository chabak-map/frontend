package com.example.myapplication.find.models

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface FindEmailRetrofitInterface {
	@POST("/members/help/email")
	fun postFindId(@Body param: FindEmailRequest) : Call<FindEmailResponse>
}