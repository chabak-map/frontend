package com.example.myapplication.post.detail.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityWritePostCommentBinding
import com.example.myapplication.post.detail.comment.adapter.WritePostCommentRecyclerview
import com.example.myapplication.post.detail.comment.models.Comments
import com.example.myapplication.post.detail.comment.models.WritePostCommentRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WritePostCommentActivity : BaseActivity<ActivityWritePostCommentBinding>(ActivityWritePostCommentBinding::inflate) {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		val postId = intent.getSerializableExtra("postId")
		getPostComment(postId as Int)
		binding.commentRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
		binding.commentRv.setHasFixedSize(true)
	}

	fun getPostComment(postId : Int){
		val writePostCommentretrofit = ApplicationClass.sRetrofit.create(WritePostCommentRetrofitInterface::class.java)
		writePostCommentretrofit.tryGetComments(postId).enqueue(object : Callback<Comments>{
			override fun onResponse(call: Call<Comments>, response: Response<Comments>) {
				val result = response.body() as Comments
				binding.commentRv.adapter = WritePostCommentRecyclerview(result)
			}

			override fun onFailure(call: Call<Comments>, t: Throwable) {
				showCustomToast(t.message.toString())
			}
		})
	}
}