package com.example.myapplication.login

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.example.myapplication.main.MainActivity
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.find.FindActivity
import com.example.myapplication.join.JoinActivity
import com.example.myapplication.login.models.LoginRequest
import com.example.myapplication.login.models.LoginResponse

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate),
    LoginActivityView {

    lateinit var editor: SharedPreferences.Editor

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        editor = ApplicationClass.sSharedPreferences.edit()
        binding.btnLogin.setOnClickListener {
            if (binding.etEmail.text.toString() == "") {
                val dialog = CustomDialog(this)
                dialog.showDialog()
            } else if (binding.etPassword.text.toString() == "") {
                val dialog = CustomDialog(this)
                dialog.showDialog()
            } else {
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()
                println(email)
                println(password)
                val postRequest = LoginRequest(email = email, password = password)
                LoginService(this).tryPostSignUp(postRequest)
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        // 아이디 찾기 클릭
        binding.findIdTv.setOnClickListener {
            startActivity(Intent(this, FindActivity::class.java))
        }

        // 가입하기 클릭
        binding.tvJoinStart.setOnClickListener {
            startActivity(Intent(this, JoinActivity::class.java))
        }
    }

    override fun onGetUserSuccess(response: LoginResponse) {
        showCustomToast("GET JWT 성공")
    }

    override fun onGetUserFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onPostSignUpSuccess(response: LoginResponse) {
        response.message?.let { showCustomToast(it) }
        editor.putString(ApplicationClass.X_ACCESS_TOKEN, response.result.jwt)
        editor.apply()
    }

    override fun onPostSignUpFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}