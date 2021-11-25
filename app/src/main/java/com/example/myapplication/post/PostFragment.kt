package com.example.myapplication.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentPostBinding
import com.example.myapplication.post.place.models.PlaceRetrofitInterface
import com.example.myapplication.post.place.models.place
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostFragment : BaseFragment<FragmentPostBinding>(FragmentPostBinding::bind, R.layout.fragment_post) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		tryGetPlace(1)
	}

	fun tryGetPlace(placeId : Int){
		val placeRetrofitInterface = ApplicationClass.sRetrofit.create(PlaceRetrofitInterface::class.java)
		placeRetrofitInterface.getPlace(placeId).enqueue(object : Callback<place>{
			override fun onResponse(call: Call<place>, response: Response<place>) {
				var result = response.body() as place
				binding.postPlaceNameTv.text = result.result.name
			}

			override fun onFailure(call: Call<place>, t: Throwable) {
				showCustomToast("${t.message}")
			}
		})
	}
}