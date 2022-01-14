package com.example.myapplication.post.write

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityWriteBinding

class WriteActivity : BaseActivity<ActivityWriteBinding>(ActivityWriteBinding::inflate) {

	private val OPEN_GALLERY = 1

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		binding.gotoMypostImg.setOnClickListener {
			finish()
		}
		binding.uploadPictureBtn.setOnClickListener {
			val intent = Intent(Intent.ACTION_GET_CONTENT)
			intent.type = "image/*"
			startActivityForResult(intent, OPEN_GALLERY)
		}
		binding.registerPostTv.setOnClickListener {
			var tag = binding.writePostTagEt.text.toString().split(",")
			Log.d("tag", tag.toString())
		}
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if(requestCode == OPEN_GALLERY){
			if (resultCode == RESULT_OK){
				var currentImageUri = data?.data
				Log.d("camera", currentImageUri.toString())
			}
		}
	}
}