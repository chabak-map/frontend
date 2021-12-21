package com.example.myapplication.join

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityJoinFinishBinding

class JoinFinishActivity :
    BaseActivity<ActivityJoinFinishBinding>(ActivityJoinFinishBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}