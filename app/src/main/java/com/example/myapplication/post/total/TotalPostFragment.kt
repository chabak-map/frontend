package com.example.myapplication.post.total

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentTotalPostBinding
import com.example.myapplication.map.detail.adapter.PostPagerAdapter
import com.example.myapplication.map.detail.models.DetailPost
import com.example.myapplication.map.detail.models.DetailPostRetrofitInterface
import com.example.myapplication.post.total.adapter.TotalPostRecyclerview
import com.example.myapplication.post.total.models.TotalPost
import com.example.myapplication.post.total.models.TotalPostRetrofitInterface
import com.example.myapplication.post.write.WriteActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TotalPostFragment : BaseFragment<FragmentTotalPostBinding>(FragmentTotalPostBinding::bind, R.layout.fragment_total_post) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.totalPostRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
		binding.totalPostRv.setHasFixedSize(true)
		binding.postTextBackground.setOnClickListener {
			startActivity(Intent(context, WriteActivity::class.java))
		}
		tryGetTotalPost()
	}

	fun tryGetTotalPost(){
		val totalPostRetrofitInterface = ApplicationClass.sRetrofit.create(TotalPostRetrofitInterface::class.java)
		totalPostRetrofitInterface.tryTotalPost().enqueue(object : Callback<TotalPost>{
			override fun onResponse(call: Call<TotalPost>, response: Response<TotalPost>) {
				val result = response.body() as TotalPost
				binding.totalPostRv.adapter = TotalPostRecyclerview(result)
			}

			override fun onFailure(call: Call<TotalPost>, t: Throwable) {
				showCustomToast(t.message.toString())
			}
		})
	}
}