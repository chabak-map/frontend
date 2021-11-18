package com.example.myapplication.map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityMapBinding

class MapActivity : BaseActivity<ActivityMapBinding>(ActivityMapBinding::inflate){
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
	}
}