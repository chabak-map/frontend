package com.example.myapplication.mypage

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentMyPageBinding
import com.example.myapplication.mypage.profile.ProfileActivityView
import com.example.myapplication.mypage.profile.ProfileService
import com.example.myapplication.mypage.profile.models.GetProfile
import com.example.myapplication.mypage.profile.models.GetProfileRetrofitInterface
import com.example.myapplication.mypage.profile.models.PostProfileRequest
import com.example.myapplication.mypage.profile.models.ProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page), ProfileActivityView {

	private val GALLERY = 0
	private var imageView : ImageView ?= null

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		imageView = binding.profileImg
		imageView?.setOnClickListener(object : View.OnClickListener{
			override fun onClick(v: View?) {
				val intent = Intent(Intent.ACTION_GET_CONTENT)
				intent.setType("image/*")
				startActivityForResult(intent, GALLERY)
			}
		})
		tryGetProfile()
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)

		if(requestCode == GALLERY){
			if(resultCode == RESULT_OK){
				var dataUri = data?.data
				try{
					var bitmap : Bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, dataUri)
					imageView?.setImageBitmap(bitmap)
					val profileRequest = PostProfileRequest(
						memberImage = imageView!!
					)
					ProfileService(this).tryPostProfile(profileRequest)
				}catch (e:Exception){
					showCustomToast(e.toString())
				}
			}
		}
	}

	override fun profileSuccess(response: ProfileResponse) {
		showCustomToast(response.result.toString())
	}

	override fun profileFailed(message: String) {
		showCustomToast(message)
	}

	fun tryGetProfile(){
		val getprofileRetrofitInterface = ApplicationClass.sRetrofit.create(GetProfileRetrofitInterface::class.java)
		getprofileRetrofitInterface.getProfile().enqueue(object : Callback<GetProfile>{
			override fun onResponse(call: Call<GetProfile>, response: Response<GetProfile>) {
				val result = response.body() as GetProfile
				binding.profileNameTv.text = result.result.nickname
			}

			override fun onFailure(call: Call<GetProfile>, t: Throwable) {
				showCustomToast(t.message.toString())
			}
		})
	}

}