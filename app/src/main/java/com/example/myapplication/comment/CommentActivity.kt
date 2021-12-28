package com.example.myapplication.comment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.comment.adapter.CommentRecyclerview
import com.example.myapplication.comment.models.CommentRetrofitInterface
import com.example.myapplication.comment.models.Comments
import com.example.myapplication.map.review.models.MapReview
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityCommentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentActivity : BaseActivity<ActivityCommentBinding>(ActivityCommentBinding::inflate) {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		binding.commentRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
		binding.commentRv.setHasFixedSize(true)
	}

	fun getTryComment(placeId : Int){
		val commentRetrofitInterface = ApplicationClass.sRetrofit.create(CommentRetrofitInterface::class.java)
		commentRetrofitInterface.tryGetComments(placeId).enqueue(object : Callback<Comments>{
			override fun onResponse(call: Call<Comments>, response: Response<Comments>) {
				val result = response.body() as Comments
				binding.commentRv.adapter = CommentRecyclerview(result)
			}

			override fun onFailure(call: Call<Comments>, t: Throwable) {
				showCustomToast(t.message.toString())
			}
		})
	}
}