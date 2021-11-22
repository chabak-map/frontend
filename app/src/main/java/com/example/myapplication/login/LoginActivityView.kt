package com.example.myapplication.login

import com.example.myapplication.login.models.LoginResponse

interface LoginActivityView {

	fun onGetUserSuccess(response: LoginResponse)

	fun onGetUserFailure(message: String)

	fun onPostSignUpSuccess(response: LoginResponse)

	fun onPostSignUpFailure(message: String)
}