package com.example.myapplication.find

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityFindPwEditBinding

class FindPwEditActivity :
    BaseActivity<ActivityFindPwEditBinding>(ActivityFindPwEditBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivFindPwBack.setOnClickListener {
            finish()
        }
    }
}