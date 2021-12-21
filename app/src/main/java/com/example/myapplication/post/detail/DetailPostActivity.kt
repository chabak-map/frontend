package com.example.myapplication.post.detail

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityDetailPostBinding
import com.example.myapplication.post.detail.adapter.PostPagerAdapter
import com.example.myapplication.post.detail.models.DetailPost
import com.example.myapplication.post.detail.models.DetailPostRetrofitInterface
import com.example.myapplication.post.detail.tagmodels.Tag
import com.example.myapplication.post.detail.tagmodels.TagRetrofitInterface
import com.example.myapplication.post.total.adapter.TotalPostRecyclerview
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

	}
	fun tryGetDetailPost(postId : Int, date : String){
		val detailPostRetrofitInterface = ApplicationClass.sRetrofit.create(DetailPostRetrofitInterface::class.java)
		detailPostRetrofitInterface.tryGetDetailPost(postId).enqueue(object : Callback<DetailPost>{
			override fun onResponse(call: Call<DetailPost>, response: Response<DetailPost>) {
				val result = response.body() as DetailPost
				for (i in 0 until result.result.postImageUrls.size) {
					imgList.add(result.result.postImageUrls[i])
				}
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

	fun tryGetDetailTag(placeId : Int){
		val tagRetrofitInterface = ApplicationClass.sRetrofit.create(TagRetrofitInterface::class.java)
		tagRetrofitInterface.getPostTag(placeId).enqueue(object : Callback<Tag>{
			override fun onResponse(call: Call<Tag>, response: Response<Tag>) {
				val result = response.body() as Tag
				binding.detailPostDetailTagTv.text = "#" + result.result
			}

			override fun onFailure(call: Call<Tag>, t: Throwable) {
				showCustomToast(t.message.toString())
			}
		})
	}
}