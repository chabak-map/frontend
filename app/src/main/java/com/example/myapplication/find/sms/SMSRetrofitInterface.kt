package com.example.myapplication.join.sms

import com.example.myapplication.join.models.PostSignUpRequest
import com.example.myapplication.join.models.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IdSMSRetrofitInterface {
    @POST("/members/sms")
    fun postSMS(@Body params: PostSmsRequest): Call<SMSResponse>
}