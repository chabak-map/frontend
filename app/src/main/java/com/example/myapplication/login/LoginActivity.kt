package com.example.myapplication.login

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.join.JoinActivity
import com.example.myapplication.login.models.LoginRequest
import com.example.myapplication.login.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate),
LoginActivityView{

	lateinit var editor : SharedPreferences.Editor

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
				val postRequest = LoginRequest(email = email, password = password)
				LoginService(this).tryPostSignUp(postRequest)
				startActivity(Intent(this, MainActivity::class.java))
			}
		}

		binding.tvJoinStart.setOnClickListener {
			startActivity(Intent(this, JoinActivity::class.java))
		}
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