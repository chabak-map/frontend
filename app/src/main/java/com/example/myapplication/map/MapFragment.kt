package com.example.myapplication.map

import android.Manifest
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
import com.naver.maps.map.*
import com.naver.maps.map.util.FusedLocationSource
import kotlinx.android.synthetic.main.activity_map.*
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
	}

	override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<out String>,
		grantResults: IntArray
	) {
		if(locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)){
			if(!locationSource.isActivated){
				naverMap.locationTrackingMode = LocationTrackingMode.None // 권한 거부
			}
			return
		}
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
	}

	override fun onMapReady(naverMap: NaverMap) {
		this.naverMap = naverMap
		naverMap.locationSource = locationSource
	}

	companion object {
		private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
	}
}