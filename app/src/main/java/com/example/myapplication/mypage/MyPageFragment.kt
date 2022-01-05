package com.example.myapplication.mypage

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentMyPageBinding
import com.example.myapplication.glide.GlideApp
import com.example.myapplication.mypage.profile.ProfileActivityView
import com.example.myapplication.mypage.profile.models.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class MyPageFragment :
	BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page),
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
				var selectedImage = data?.data
				var dataUri = data?.data
				var bitmap: Bitmap? = null
				try {
					bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, dataUri)
					binding.profileImg.setImageBitmap(bitmap)
					var cursor: Cursor? = context?.contentResolver?.query(
						Uri.parse(selectedImage.toString()),
						null,
						null,
						null,
						null
					)
					assert(cursor != null)
					cursor?.moveToFirst()
					var mediaPath =
						cursor?.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA))
					var file = File(mediaPath)
					var requestBody: RequestBody =
						RequestBody.create("multipart/form-data".toMediaTypeOrNull(),  file)
					println(requestBody)
					var profile =
						MultipartBody.Part.createFormData("file", requestBody.toString())
					var postProfile = PostProfileRequest(profile)
					println(postProfile)
//					getProfile(postProfile)
				} catch (e: Exception) {
					showCustomToast(e.toString())
				}
			}
		}
	}

	fun getProfile(body: PostProfileRequest) {
		var profileRetrofitInterface =
			ApplicationClass.sRetrofit.create(ProfileRetrofitInterface::class.java)
		profileRetrofitInterface.postProfile(body).enqueue(object : Callback<ProfileResponse> {
			override fun onResponse(
				call: Call<ProfileResponse>,
				response: Response<ProfileResponse>
			) {
				var result = response.body() as ProfileResponse
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
			override fun onResponse(call: Call<GetProfile>, response: Response<GetProfile>) {
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