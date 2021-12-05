package com.example.myapplication.login

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import com.example.myapplication.R
import kotlinx.android.synthetic.main.login_custom_dialog.*

class CustomDialog(context : Context) {

	private val dialog = Dialog(context)

	fun showDialog(){
		dialog.setContentView(R.layout.login_custom_dialog)
		dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 배경 투명
		dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
		dialog.setCanceledOnTouchOutside(true)
		dialog.setCancelable(true)
		dialog.show()

		dialog.login_ok_btn.setOnClickListener {
			dialog.dismiss()
		}
	}

}