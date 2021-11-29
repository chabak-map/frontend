package com.example.myapplication.post.place

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentPlaceBinding
import com.example.myapplication.post.place.models.PlaceRetrofitInterface
import com.example.myapplication.post.place.models.Place
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
		placeRetrofitInterface.getPlace(placeId).enqueue(object : Callback<Place>{
			@SuppressLint("SetTextI18n")
			override fun onResponse(call: Call<Place>, response: Response<Place>) {
				var result = response.body() as Place
				binding.postPlaceNameTv.text = result.result.name
				binding.postPlaceAddressTv.text = result.result.address
				binding.postPlaceReviewCntTv.text = "${R.string.review_cnt} " + result.result.reviewCount.toString()
			}

			override fun onFailure(call: Call<Place>, t: Throwable) {
				showCustomToast("${t.message}")
			}
		})
	}
}