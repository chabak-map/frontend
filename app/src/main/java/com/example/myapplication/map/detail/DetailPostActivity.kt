package com.example.myapplication.map.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityDetailPostBinding
import com.example.myapplication.map.adapter.RadiusPlaceRecyclerView
import com.example.myapplication.map.detail.adapter.PostPagerAdapter
import com.example.myapplication.map.detail.models.DetailPost
import com.example.myapplication.map.detail.models.DetailPostRetrofitInterface
import com.example.myapplication.map.detail.tagmodels.Tag
import com.example.myapplication.map.detail.tagmodels.TagRetrofitInterface
import com.example.myapplication.map.models.RadiusPlace
import com.example.myapplication.map.models.Result
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
		tryGetDetailPost(datas as Int)
		tryGetDetailTag(datas as Int)

	}
	fun tryGetDetailPost(postId : Int){
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

				binding.detailPlaceVp.adapter = PostPagerAdapter(this@DetailPostActivity, imgList)
				binding.detailPlaceVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
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