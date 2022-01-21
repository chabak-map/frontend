package com.example.myapplication.post.write

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityWriteBinding
import com.example.myapplication.post.write.models.WritePost
import com.example.myapplication.post.write.models.WritePostRequest
import com.example.myapplication.post.write.models.WritePostRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
			val title = binding.writePostTitleEt.text.toString()
			val post = binding.writePostContentEt.text.toString()
			val writepostRequest = WritePostRequest(title = title, content = post)
			tryWritePost(writepostRequest)
			finish()
		}
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (requestCode == OPEN_GALLERY) {
			if (resultCode == RESULT_OK) {
				var currentImageUri = data?.data
				Log.d("camera", currentImageUri.toString())
			}
		}
	}

	fun tryWritePost(writePostRequest: WritePostRequest) {
		val writePostRetrofitInterface =
			ApplicationClass.sRetrofit.create(WritePostRetrofitInterface::class.java)
		writePostRetrofitInterface.tryWritePost(writePostRequest).enqueue(object :
			Callback<WritePost> {
			override fun onResponse(call: Call<WritePost>, response: Response<WritePost>) {
				val result = response.body() as WritePost
			}

			override fun onFailure(call: Call<WritePost>, t: Throwable) {
				showCustomToast(t.message.toString())
			}
		})
	}
}