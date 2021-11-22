package com.example.myapplication.join

import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.join.models.PostSignUpRequest
import com.example.myapplication.join.models.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinService(val view : JoinActivityView) {

	fun tryPostSignUp(postSignUpRequest: PostSignUpRequest){
		val joinRetrofitInterface = ApplicationClass.sRetrofit.create(JoinRetrofitInterface::class.java)
		joinRetrofitInterface.postSignUp(postSignUpRequest).enqueue(object :
			Callback<SignUpResponse> {

			override fun onResponse(
				call: Call<SignUpResponse>,
				response: Response<SignUpResponse>
			) {
				view.onPostSignUpSuccess(response.body() as SignUpResponse)
			}

			override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
				view.onPostSignUpFailure(t.message ?: "통신오류")
			}
		})
	}
}