package com.example.myapplication.login

import com.example.myapplication.login.models.LoginRequest
import com.example.myapplication.login.models.LoginResponse
import retrofit2.Call

import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRetrofitInterface {
	@POST("/members/login")
	fun postLogin(@Body params: LoginRequest) : Call<LoginResponse>
}