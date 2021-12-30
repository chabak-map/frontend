package com.example.myapplication.map.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentMapDetailBinding
import com.example.myapplication.map.detail.adapter.MapCommentRecyclerView
import com.example.myapplication.map.detail.adapter.MapDetailTagRecyclerView
import com.example.myapplication.map.detail.models.MapDetail
import com.example.myapplication.map.detail.models.MapDetailRetrofitInterface
import com.example.myapplication.map.detail.review.MapReviewActivity
import com.example.myapplication.post.detail.DetailPostActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapDetailFragment(placeId: Int) : BaseFragment<FragmentMapDetailBinding>(
	FragmentMapDetailBinding::bind,
	R.layout.fragment_map_detail
) {

	private val place = placeId
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		getMapDetail(place)
		binding.mapDetailReviewRv.layoutManager =
			LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
		binding.mapDetailReviewRv.setHasFixedSize(true)
		binding.mapDetailTagRv.layoutManager =
			LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
		binding.mapDetailTagRv.setHasFixedSize(true)
		binding.gotoWriteMapReviewBtn.setOnClickListener {
			startActivity(
				Intent(
					requireContext(),
					MapReviewActivity::class.java
				).apply {
					putExtra("MapPlaceId", place)
				}
			)
		}
	}

	fun getMapDetail(placeId: Int) {
		val mapDetailRetrofitInterface =
			ApplicationClass.sRetrofit.create(MapDetailRetrofitInterface::class.java)
		mapDetailRetrofitInterface.getMapDetail(placeId).enqueue(object : Callback<MapDetail> {
			@SuppressLint("SetTextI18n")
			override fun onResponse(call: Call<MapDetail>, response: Response<MapDetail>) {
				val result = response.body() as MapDetail
				binding.mapDetailReviewRv.adapter = MapCommentRecyclerView(result)
				binding.mapDetailNameTv.text = result.result.name
				binding.mapDetailReviewCntTv.text =
					binding.mapDetailReviewCntTv.text.toString() + result.result.reviewCount.toString()
				binding.mapDetailCallTv.text = result.result.phoneNumber
				binding.mapDetailAddTv.text = result.result.address
				binding.mapDetailReviewTv.text =
					binding.mapDetailReviewTv.text.toString() + result.result.reviewCount.toString()
				binding.mapDetailTagRv.adapter = MapDetailTagRecyclerView(result)
			}

			override fun onFailure(call: Call<MapDetail>, t: Throwable) {
				showCustomToast(t.message.toString())
			}
		})
	}
}