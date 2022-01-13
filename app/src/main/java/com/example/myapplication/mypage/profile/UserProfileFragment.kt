package com.example.myapplication.mypage.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentUserProfileBinding
import com.example.myapplication.glide.GlideApp
import com.example.myapplication.mypage.profile.models.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UserProfileFragment :
	BaseFragment<FragmentUserProfileBinding>(FragmentUserProfileBinding::bind, R.layout.fragment_user_profile),
	ProfileActivityView {

	private val GALLERY = 0

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.profileImg.setOnClickListener {
			val intent = Intent(
				Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
			)
			intent.type = "image/*"
			startActivityForResult(intent, GALLERY)
		}
		tryGetProfile()
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)

		if (requestCode == GALLERY) {
			if (resultCode == RESULT_OK) {
				val dataUri = data?.data
				Log.d("picture", dataUri.toString())
				val file = File(dataUri.toString())
				var requestBody : RequestBody = RequestBody.create(
					"image/*".toMediaTypeOrNull(),
					file
				)
				var body : MultipartBody.Part = MultipartBody.Part.createFormData("uploaded_file",
					requestBody.toString()
				)
				Log.d("picture", body.toString())
				getProfile(body)
			}
		}
	}

	fun getProfile(body : MultipartBody.Part) {
		var profileRetrofitInterface =
			ApplicationClass.sRetrofit.create(ProfileRetrofitInterface::class.java)
		profileRetrofitInterface.postProfile(body)
			.enqueue(object : Callback<ProfileResponse> {
				override fun onResponse(
					call: Call<ProfileResponse>,
					response: Response<ProfileResponse>
				) {
					val result = response.body() as ProfileResponse
					Log.d("picture", result.toString())
					showCustomToast(result.toString())
				}

				override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
					showCustomToast(t.message.toString())
				}
			})
	}

	override fun profileSuccess(response: ProfileResponse) {
		showCustomToast(response.result.toString())
	}

	override fun profileFailed(message: String) {
		showCustomToast(message)
	}

	fun tryGetProfile() {
		val getprofileRetrofitInterface =
			ApplicationClass.sRetrofit.create(GetProfileRetrofitInterface::class.java)
		getprofileRetrofitInterface.getProfile().enqueue(object : Callback<GetProfile> {
			override fun onResponse(
				call: Call<GetProfile>,
				response: Response<GetProfile>
			) {
				val result = response.body() as GetProfile
				binding.profileNameTv.text = result.result.nickname
				GlideApp.with(requireContext()).load(result.result.imageUrl)
					.into(binding.profileImg)
			}

			override fun onFailure(call: Call<GetProfile>, t: Throwable) {
				showCustomToast(t.message.toString())
			}
		})
	}

}