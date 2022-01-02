package com.example.myapplication.post.detail.comment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityWritePostCommentBinding
import com.example.myapplication.post.detail.comment.adapter.WritePostCommentRecyclerview
import com.example.myapplication.post.detail.comment.models.Comments
import com.example.myapplication.post.detail.comment.models.WritePostCommentRetrofitInterface
import com.example.myapplication.post.detail.comment.write.models.WritePostCommentRequest
import com.example.myapplication.post.detail.comment.write.models.WritePostCommentResponse
import com.example.myapplication.post.detail.comment.write.models.WritePostCommentService
import com.example.myapplication.post.detail.comment.write.models.WritePostCommentView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WritePostCommentActivity : BaseActivity<ActivityWritePostCommentBinding>(ActivityWritePostCommentBinding::inflate), WritePostCommentView {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		val postId = intent.getSerializableExtra("postId")
		getPostComment(postId as Int)
		binding.commentRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
		binding.commentRv.setHasFixedSize(true)
		binding.commentEnterBtn.setOnClickListener {
			val review = binding.commentEt.text.toString()
			val write = WritePostCommentRequest(content = review)
			WritePostCommentService(this).tryWriteComment(write, postId)
			binding.commentEnterBtn.text = ""
		}
		binding.gotopostImg.setOnClickListener {
			finish()
		}
	}

	fun getPostComment(postId : Int){
		val writePostCommentRetrofit = ApplicationClass.sRetrofit.create(WritePostCommentRetrofitInterface::class.java)
		writePostCommentRetrofit.tryGetComments(postId).enqueue(object : Callback<Comments>{
			override fun onResponse(call: Call<Comments>, response: Response<Comments>) {
				val result = response.body() as Comments
				binding.commentRv.adapter = WritePostCommentRecyclerview(result)
			}

			override fun onFailure(call: Call<Comments>, t: Throwable) {
				showCustomToast(t.message.toString())
			}
		})
	}

	override fun onPostWriteSuccess(response: WritePostCommentResponse) {
		showCustomToast(response.result.toString())
	}

	override fun onPostWriteFailure(message: String) {
		showCustomToast(message.toString())
	}
}