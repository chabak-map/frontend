package com.example.myapplication.mypage.profile

import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.mypage.profile.models.PostProfileRequest
import com.example.myapplication.mypage.profile.models.ProfileResponse
import com.example.myapplication.mypage.profile.models.ProfileRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileService(val view : ProfileActivityView) {
	
	fun tryPostProfile(postProfileRequest : PostProfileRequest){
		val profileRetrofitInterface = ApplicationClass.sRetrofit.create(ProfileRetrofitInterface::class.java)
		profileRetrofitInterface.postProfile(postProfileRequest).enqueue(object : Callback<ProfileResponse>{
			override fun onResponse(
				call: Call<ProfileResponse>,
				response: Response<ProfileResponse>
			) {
				view.profileSuccess(response.body() as ProfileResponse)
			}

			override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
				view.profileFailed(t.message ?: "통신오류")
			}
		})
	}
}