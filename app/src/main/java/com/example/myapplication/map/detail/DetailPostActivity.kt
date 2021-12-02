package com.example.myapplication.map.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityDetailPostBinding
import com.example.myapplication.map.adapter.RadiusPlaceRecyclerView
import com.example.myapplication.map.models.RadiusPlace
import com.example.myapplication.map.models.Result

class DetailPostActivity : BaseActivity<ActivityDetailPostBinding>(ActivityDetailPostBinding::inflate) {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		var datas = intent.getSerializableExtra("data")

		println(datas)
	}
}