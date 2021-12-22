package com.example.myapplication.login

import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.login.models.LoginRequest
import com.example.myapplication.login.models.LoginResponse
import retrofit2.Call
import retrofit2.Response

class LoginService(val view : LoginActivityView) {

	fun tryPostSignUp(postLoginRequest: LoginRequest){
		val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
		loginRetrofitInterface.postLogin(postLoginRequest).enqueue(object :
			retrofit2.Callback<LoginResponse> {
			override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
				view.onPostSignUpSuccess(response.body() as LoginResponse)
			}

			override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
				view.onPostSignUpFailure(t.message ?: "통신 오류")
			}
		})
	}
}