package com.example.myapplication.map

import android.annotation.SuppressLint
import android.app.FragmentManager
import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentMapBinding
import com.example.myapplication.map.adapter.RadiusPlaceRecyclerView
import com.example.myapplication.map.detail.DetailPostActivity
import com.example.myapplication.map.models.RadiusPlace
import com.example.myapplication.map.models.RadiusPlaceRetrofitInterface
import com.example.myapplication.map.models.Result
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.MapFragment
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.widget.LocationButtonView
import kotlinx.android.synthetic.main.fragment_map.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class MapFragment :
	BaseFragment<FragmentMapBinding>(FragmentMapBinding::bind, R.layout.fragment_map),
	OnMapReadyCallback {

	private lateinit var locationSource: FusedLocationSource
	private lateinit var naverMap: NaverMap
	var now_lat: Double? = null
	var now_long: Double? = null
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val fm = fragmentManager
		val mapFragment = fm?.findFragmentById(R.id.map) as MapFragment?
			?: MapFragment.newInstance(NaverMapOptions().locationButtonEnabled(false))
				.also {
					fm?.beginTransaction()?.add(R.id.map, it)?.commit()
				}
		mapFragment.getMapAsync(this)

		binding.mapSearchEt.bringToFront()
		binding.searchPlaceImg.bringToFront()
		binding.itemRadiusPlaceRv.layoutManager =
			LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
		binding.itemRadiusPlaceRv.setHasFixedSize(true)

	}

	override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<String>,
		grantResults: IntArray
	) {
		if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
			if (!locationSource.isActivated) {
				naverMap.locationTrackingMode = LocationTrackingMode.None // 권한 거부
			} else {
				naverMap.locationTrackingMode = LocationTrackingMode.Follow
			}
			return
		}
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
	}

	override fun onMapReady(naverMap: NaverMap) {

		locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
		this.naverMap = naverMap
		naverMap.locationSource = locationSource
		val uiSettings = naverMap.uiSettings
		uiSettings.isZoomControlEnabled = false

		val locationButtonView = location_btn as LocationButtonView
		locationButtonView.map = naverMap
		val geocoder = Geocoder(requireContext())
		val infoWindow = InfoWindow()

		binding.nowLocationBtn.setOnClickListener {
			tryGetPlace(naverMap.cameraPosition.target.latitude, naverMap.cameraPosition.target.longitude, 40)
		}

		binding.searchPlaceImg.setOnClickListener {
			val address = binding.mapSearchEt.text.toString()
			val address_geo = geocoder.getFromLocationName(address, 1)
			val marker = Marker()
			marker.position = LatLng(address_geo[0].latitude, address_geo[0].longitude)
			marker.map = naverMap
			marker.tag = address
			marker.setOnClickListener {
				infoWindow.open(marker)
				true
			}
		}

		naverMap.addOnLocationChangeListener { location ->
			now_lat = location.latitude
			now_long = location.longitude
			tryGetPlace(now_lat!!, now_long!!, 40)
			locationSource.deactivate()
		}


		infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(requireContext()) {
			override fun getText(infoWindow: InfoWindow): CharSequence {
				return infoWindow.marker?.tag as CharSequence? ?: ""
			}
		}
	}

	fun tryGetPlace(lat: Double, lng: Double, r: Int) {
		val radiusPlaceRetrofitInterface =
			ApplicationClass.sRetrofit.create(RadiusPlaceRetrofitInterface::class.java)
		radiusPlaceRetrofitInterface.getPlace(lat, lng, r)
			.enqueue(object : Callback<RadiusPlace> {
				@SuppressLint("SetTextI18n")
				override fun onResponse(
					call: Call<RadiusPlace>,
					response: Response<RadiusPlace>
				) {
					val result = response.body() as RadiusPlace
					val radiusPlaceRecyclerView = RadiusPlaceRecyclerView(result)
					binding.contentCntTv.text = "주변 장소 " + result.result.size.toString() + "개"
					binding.itemRadiusPlaceRv.adapter = radiusPlaceRecyclerView
//					radiusPlaceRecyclerView.setItemClickListener(object :
//						RadiusPlaceRecyclerView.OnItemClickListener {
//						override fun onClick(v: View, position: Int, data: Result) {
//							startActivity(
//								Intent(
//									context,
//									DetailPostActivity::class.java
//								).apply {
//									putExtra("data", data.placeId)
//									addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//								})
//						}
//					})
				}

				override fun onFailure(call: Call<RadiusPlace>, t: Throwable) {
					showCustomToast("${t.message}")
				}
			})

	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)

	}

	companion object {
		private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
	}


}