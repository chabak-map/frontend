package com.example.myapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.join.JoinActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		binding.gotoJoinTv.setOnClickListener {
			startActivity(Intent(this, JoinActivity::class.java))
		}
	}
}