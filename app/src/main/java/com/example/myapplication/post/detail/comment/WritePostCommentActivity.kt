package com.example.myapplication.post.detail.comment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityWritePostCommentBinding
import com.example.myapplication.post.detail.comment.adapter.WritePostCommentRecyclerview
import com.example.myapplication.post.detail.comment.models.Comments
import com.example.myapplication.post.detail.comment.models.WritePostCommentRetrofitInterface
import com.example.myapplication.post.detail.comment.write.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WritePostCommentActivity : BaseActivity<ActivityWritePostCommentBinding>(ActivityWritePostCommentBinding::inflate){
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
			tryWriteComment(write, postId)
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

	fun tryWriteComment(writePostComment: WritePostCommentRequest, postId: Int){
		val writePostCommentRetrofitInterface = ApplicationClass.sRetrofit.create(com.example.myapplication.post.detail.comment.write.models.WritePostCommentRetrofitInterface::class.java)
		writePostCommentRetrofitInterface.tryPostComments(writePostComment, postId).enqueue(object : Callback<WritePostCommentResponse>{
			override fun onResponse(
				call: Call<WritePostCommentResponse>,
				response: Response<WritePostCommentResponse>
			) {
				val result = response.body() as ResultWritePostComment
			}

			override fun onFailure(call: Call<WritePostCommentResponse>, t: Throwable) {
				showCustomToast(t.message.toString())
			}
		})
	}
}