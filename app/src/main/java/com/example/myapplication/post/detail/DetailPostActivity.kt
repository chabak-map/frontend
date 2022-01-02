package com.example.myapplication.post.detail

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityDetailPostBinding
import com.example.myapplication.glide.GlideApp
import com.example.myapplication.map.detail.review.MapReviewActivity
import com.example.myapplication.post.detail.comment.WritePostCommentActivity
import com.example.myapplication.post.detail.models.DetailPost
import com.example.myapplication.post.detail.models.DetailPostRetrofitInterface
import com.example.myapplication.post.tag.models.PostTag
import com.example.myapplication.post.tag.models.PostTagRetrofitInterface
import kotlinx.android.synthetic.main.detail_post_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPostActivity : BaseActivity<ActivityDetailPostBinding>(ActivityDetailPostBinding::inflate) {
	val imgList = ArrayList<String>()
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		val datas = intent.getSerializableExtra("data")
		val date = intent.getSerializableExtra("date")
		tryGetDetailPost(datas as Int, date as String)
		binding.postDetailTagRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
		binding.postDetailTagRv.setHasFixedSize(true)
		binding.backArrowImg.setOnClickListener {
			finish()
		}
		binding.detailPostCommentImg.setOnClickListener {
			startActivity(
				Intent(
					baseContext,
					WritePostCommentActivity::class.java
				).apply {
					putExtra("postId", datas)
				}
			)
		}
	}
	fun tryGetDetailPost(postId : Int, date : String){
		val detailPostRetrofitInterface = ApplicationClass.sRetrofit.create(DetailPostRetrofitInterface::class.java)
		detailPostRetrofitInterface.tryGetDetailPost(postId).enqueue(object : Callback<DetailPost>{
			override fun onResponse(call: Call<DetailPost>, response: Response<DetailPost>) {
				val result = response.body() as DetailPost
				GlideApp.with(this@DetailPostActivity).load(result.result.postImageUrls[0]).into(binding.postDetailImg)
				binding.detailPostTitleTv.text = result.result.title
				binding.detailPostWriterTv.text = result.result.nickname
				binding.detailPostContentTv.text = result.result.content
				binding.detailPostDateTv.text = date
			}

			override fun onFailure(call: Call<DetailPost>, t: Throwable) {
				showCustomToast("${t.message}")
			}
		})
	}
}