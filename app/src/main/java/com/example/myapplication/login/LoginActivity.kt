package com.example.myapplication.login

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.join.JoinActivity
import java.util.*

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)


		binding.btnLogin.setOnClickListener {
			if (binding.etEmail.text.toString() == "") {
				val dialog = CustomDialog(this)
				dialog.showDialog()
			} else if (binding.etPassword.text.toString() == "") {
				val dialog = CustomDialog(this)
				dialog.showDialog()
			} else {
				startActivity(Intent(this, MainActivity::class.java))
			}
		}
	}
}