package com.example.myapplication.post.place

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentPlaceBinding
import com.example.myapplication.post.place.models.PlaceRetrofitInterface
import com.example.myapplication.post.place.models.place
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaceFragment : BaseFragment<FragmentPlaceBinding>(FragmentPlaceBinding::bind, R.layout.fragment_place) {
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		tryGetPlace(1)
	}

	fun tryGetPlace(placeId : Int){
		val placeRetrofitInterface = ApplicationClass.sRetrofit.create(PlaceRetrofitInterface::class.java)
		placeRetrofitInterface.getPlace(placeId).enqueue(object : Callback<place>{
			@SuppressLint("SetTextI18n")
			override fun onResponse(call: Call<place>, response: Response<place>) {
				var result = response.body() as place
				binding.postPlaceNameTv.text = result.result.name
				binding.postPlaceAddressTv.text = result.result.address
				binding.postPlaceReviewCntTv.text = "${R.string.review_cnt} " + result.result.reviewCount.toString()
			}

			override fun onFailure(call: Call<place>, t: Throwable) {
				showCustomToast("${t.message}")
			}
		})
	}
}