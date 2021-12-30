package com.example.myapplication.map.detail.review

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityMapReviewBinding
import com.example.myapplication.map.detail.review.adapter.MapReviewRecyclerView
import com.example.myapplication.map.detail.review.models.MapReview
import com.example.myapplication.map.detail.review.models.MapReviewRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapReviewActivity() :
	BaseActivity<ActivityMapReviewBinding>(ActivityMapReviewBinding::inflate) {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val placeId = intent.getSerializableExtra("MapPlaceId")
		setContentView(binding.root)
		binding.mapReviewCommentRv.layoutManager =
			LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
		binding.mapReviewCommentRv.setHasFixedSize(true)
		binding.gotoDetailMapImg.setOnClickListener {
			finish()
		}
		getMapReview(placeId as Int)
	}

	fun getMapReview(placeId: Int) {
		val mapReviewRetrofitInterface =
			ApplicationClass.sRetrofit.create(MapReviewRetrofitInterface::class.java)
		mapReviewRetrofitInterface.getMapReview(placeId).enqueue(object : Callback<MapReview> {
			override fun onResponse(call: Call<MapReview>, response: Response<MapReview>) {
				val result = response.body() as MapReview
				binding.mapReviewCommentRv.adapter = MapReviewRecyclerView(result)
			}

			override fun onFailure(call: Call<MapReview>, t: Throwable) {
				showCustomToast(t.message.toString())
			}
		})
	}
}