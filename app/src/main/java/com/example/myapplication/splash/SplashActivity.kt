package com.example.myapplication.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivitySplashBinding
import com.example.myapplication.login.LoginActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		Handler(Looper.getMainLooper()).postDelayed({
			startActivity(Intent(this, LoginActivity::class.java))
			finish()
		}, 1500)
	}
}