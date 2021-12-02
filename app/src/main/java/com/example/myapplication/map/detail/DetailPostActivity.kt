package com.example.myapplication.map.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityDetailPostBinding
import com.example.myapplication.map.adapter.RadiusPlaceRecyclerView
import com.example.myapplication.map.detail.adapter.PostPagerAdapter
import com.example.myapplication.map.detail.models.DetailPost
import com.example.myapplication.map.detail.models.DetailPostRetrofitInterface
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

	}

	fun tryGetDetailPost(postId : Int){
		val detailPostRetrofitInterface = ApplicationClass.sRetrofit.create(DetailPostRetrofitInterface::class.java)
		detailPostRetrofitInterface.tryGetDetailPost(postId).enqueue(object : Callback<DetailPost>{
			override fun onResponse(call: Call<DetailPost>, response: Response<DetailPost>) {
				val result = response.body() as DetailPost
				binding.detailPostTitleTv.text = result.result.title
				binding.detailPostWriterTv.text = result.result.nickname
				binding.detailPostContentTv.text = result.result.content
				imgList.add(result.result.postImageUrls.toString())
				binding.detailPlaceVp.adapter = PostPagerAdapter(baseContext, imgList)
				binding.detailPlaceVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
			}

			override fun onFailure(call: Call<DetailPost>, t: Throwable) {
				showCustomToast("${t.message}")
			}
		})
	}
}