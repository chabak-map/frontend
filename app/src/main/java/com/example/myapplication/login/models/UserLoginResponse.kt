package com.example.myapplication.login.models

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(
	@SerializedName("isSuccess") val isSuccess: Boolean,
	@SerializedName("code") val code: Int,
	@SerializedName("message") val message: String,
	@SerializedName("result") val result: ArrayList<ResultLoginUser>)
