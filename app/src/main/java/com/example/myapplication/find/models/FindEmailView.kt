package com.example.myapplication.find.models

interface FindEmailView {
	fun onPostSignUpSuccess(response: FindEmailResponse)

	fun onPostSignUpFailure(message: String)
}