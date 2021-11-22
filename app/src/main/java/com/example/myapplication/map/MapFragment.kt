package com.example.myapplication.map

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.myapplication.R
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentMapBinding
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.UiSettings
import com.naver.maps.map.util.FusedLocationSource
import java.security.Permissions

class MapFragment : BaseFragment<FragmentMapBinding>(FragmentMapBinding::bind, R.layout.fragment_map),
OnMapReadyCallback{

	private val PERMISSION_REQUEST_CODE = 1000
	private lateinit var mlocationSource : FusedLocationSource
	private lateinit var mnaverMap: NaverMap

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		mlocationSource = FusedLocationSource(this, PERMISSION_REQUEST_CODE)
	}

	override fun onMapReady(p0: NaverMap) {
		mnaverMap.locationSource = mlocationSource
		mnaverMap.locationTrackingMode = LocationTrackingMode.Follow
	}
}