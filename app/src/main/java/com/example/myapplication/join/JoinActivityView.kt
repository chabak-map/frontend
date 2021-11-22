package com.example.myapplication.join

import com.example.myapplication.join.models.SignUpResponse

interface JoinActivityView {

	fun onPostSignUpSuccess(response: SignUpResponse)

	fun onPostSignUpFailure(message: String)
}