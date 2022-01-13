package com.example.myapplication.main

import android.os.Bundle
import android.view.MenuItem
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.home.HomeFragment
import com.example.myapplication.main.models.TotalSearch
import com.example.myapplication.main.models.TotalSearchRetrofitInterface
import com.example.myapplication.map.MapFragment
import com.example.myapplication.mypage.MyPageFragment
import com.example.myapplication.mypage.profile.UserProfileFragment
import com.example.myapplication.post.PostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
	BottomNavigationView.OnNavigationItemSelectedListener {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		binding.bottomNavigation.setOnNavigationItemSelectedListener(this)
		// 켜졌을 때 선택되는 첫화면
		binding.bottomNavigation.selectedItemId = R.id.home
		binding.searchBtn.setOnClickListener {
			val search = binding.totalSearchEt.text.toString()
			tryTotalSearch(search)
		}
	}

	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.home -> {
				supportFragmentManager.beginTransaction().replace(R.id.main_frame, HomeFragment())
					.commit()
				return true
			}

			R.id.map -> {
				supportFragmentManager.beginTransaction().replace(R.id.main_frame, MapFragment())
					.commit()
				return true
			}

			R.id.review -> {
				supportFragmentManager.beginTransaction().replace(R.id.main_frame, PostFragment())
					.commit()
				return true
			}
			R.id.mypage -> {
				supportFragmentManager.beginTransaction().replace(R.id.main_frame, MyPageFragment())
					.commit()
				return true
			}
		}
		return false
	}

	fun tryTotalSearch(query : String){
		val totalSearchRetrofitInterface = ApplicationClass.sRetrofit.create(TotalSearchRetrofitInterface::class.java)
		totalSearchRetrofitInterface.tryTotalSearch(query).enqueue(object : Callback<TotalSearch>{
			override fun onResponse(call: Call<TotalSearch>, response: Response<TotalSearch>) {
				val result = response.body() as TotalSearch
				println(result)
			}

			override fun onFailure(call: Call<TotalSearch>, t: Throwable) {
				showCustomToast(t.message.toString())
			}
		})
	}

}