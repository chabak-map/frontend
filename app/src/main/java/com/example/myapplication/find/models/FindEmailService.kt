package com.example.myapplication.find.models

import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.login.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindEmailService(val view : FindEmailView) {

	fun tryFindEmail(findEmailRequest: FindEmailRequest){
		val findEmailRetrofitInterface = ApplicationClass.sRetrofit.create(FindEmailRetrofitInterface::class.java)
		findEmailRetrofitInterface.postFindId(findEmailRequest).enqueue(object :
			Callback<FindEmailResponse>{
			override fun onResponse(
				call: Call<FindEmailResponse>,
				response: Response<FindEmailResponse>
			) {
				view.onPostSignUpSuccess(response.body() as FindEmailResponse)
			}

			override fun onFailure(call: Call<FindEmailResponse>, t: Throwable) {
				view.onPostSignUpFailure(t.message ?: "통신 오류")
			}
		})
	}
}