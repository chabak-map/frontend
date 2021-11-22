package com.example.myapplication.join.verify

import com.google.gson.annotations.SerializedName

data class ResultVerify(
	@SerializedName("result") val result: Boolean
)