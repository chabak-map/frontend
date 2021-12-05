package com.example.myapplication.post.write

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityWriteBinding

class WriteActivity : BaseActivity<ActivityWriteBinding>(ActivityWriteBinding::inflate) {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
	}
}