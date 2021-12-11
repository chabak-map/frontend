package com.example.myapplication.post.write

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityWriteBinding

class WriteActivity : BaseActivity<ActivityWriteBinding>(ActivityWriteBinding::inflate) {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		binding.uploadImg.setOnClickListener {
			var intent = Intent(Intent.ACTION_PICK)
			intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
			intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
			intent.action = Intent.ACTION_GET_CONTENT

			startActivityForResult(intent, 200)
		}
	}

//	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//		super.onActivityResult(requestCode, resultCode, data)
//
//		if(resultCode == RESULT_OK && requestCode == 200){
//
//			if(data?.clipData != null) { // 사진을 여러개 선택한 경우
//				val count = data.clipData!!.itemCount
//				if(count > 10){
//					showCustomToast("사진은 10장까지 선택 가능합니다.")
//					return
//				}
//				for (i in 0 until count){
//					val imageUri = data.clipData!!.getItemAt(i).uri
//				}
//			}
//
//		}
//	}
}