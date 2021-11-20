package com.example.myapplication.login

import android.content.Intent
import android.os.Bundle
import com.example.myapplication.MainActivity
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.join.JoinActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //로그인 버튼 클릭 시
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        //가입하기 버튼 클릭 시
        binding.tvJoinStart.setOnClickListener {
            startActivity(Intent(this, JoinActivity::class.java))
        }
    }
}