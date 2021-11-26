package com.example.myapplication.map

import android.Manifest
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import com.example.myapplication.R
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentMapBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.MapFragment
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.widget.LocationButtonView
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_map.map
import java.security.Permissions

class MapFragment : BaseFragment<FragmentMapBinding>(FragmentMapBinding::bind, R.layout.fragment_map),
OnMapReadyCallback{

	private lateinit var locationSource : FusedLocationSource
	private lateinit var naverMap: NaverMap

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

		val fm = fragmentManager
		val mapFragment = fm?.findFragmentById(R.id.map) as MapFragment?
			?: MapFragment.newInstance(NaverMapOptions().locationButtonEnabled(false))
				.also {
					fm?.beginTransaction()?.add(R.id.map, it)?.commit()
				}
		mapFragment.getMapAsync {
			val locationButtonView = R.id.location_btn as LocationButtonView
			locationButtonView.map = naverMap
		}

		binding.searchLl.bringToFront()

	}

	override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<String>,
		grantResults: IntArray
	) {
		if(locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)){
			if(!locationSource.isActivated){
				naverMap.locationTrackingMode = LocationTrackingMode.None // 권한 거부
			}
			else {
				naverMap.locationTrackingMode = LocationTrackingMode.Follow
				println(locationSource)
			}
			return
		}
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
	}

	override fun onMapReady(naverMap: NaverMap) {
		this.naverMap = naverMap
		naverMap.locationSource = locationSource

		val geocoder = Geocoder(requireContext())
		val infoWindow = InfoWindow()
		binding.searchBtn.setOnClickListener {
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

		infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(requireContext()) {
			override fun getText(infoWindow: InfoWindow): CharSequence {
				return infoWindow.marker?.tag as CharSequence? ?: ""
			}
		}
	}

	companion object {
		private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
	}
}