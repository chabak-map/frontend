package com.example.myapplication.login

import com.example.myapplication.login.models.LoginResponse

interface LoginActivityView {

	fun onPostSignUpSuccess(response: LoginResponse)

	fun onPostSignUpFailure(message: String)
}