package com.example.myapplication.post.total

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentTotalPostBinding
import com.example.myapplication.post.detail.DetailPostActivity
import com.example.myapplication.post.total.adapter.TotalPostRecyclerview
import com.example.myapplication.post.total.models.Result
import com.example.myapplication.post.total.models.TotalPost
import com.example.myapplication.post.total.models.TotalPostRetrofitInterface
import com.example.myapplication.post.write.WriteActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TotalPostFragment : BaseFragment<FragmentTotalPostBinding>(
    FragmentTotalPostBinding::bind,
    R.layout.fragment_total_post
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.totalPostRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.totalPostRv.setHasFixedSize(true)

        binding.postTextBackground.setOnClickListener {
            startActivity(Intent(context, WriteActivity::class.java))
        }

        // 플로팅 버튼 클릭 시
        binding.gotoWritePostFbtn.setOnClickListener {
            startActivity(Intent(requireContext(), WriteActivity::class.java))
        }

        tryGetTotalPost()
    }

    fun tryGetTotalPost() {
        val totalPostRetrofitInterface =
            ApplicationClass.sRetrofit.create(TotalPostRetrofitInterface::class.java)
        totalPostRetrofitInterface.tryTotalPost().enqueue(object : Callback<TotalPost> {
            override fun onResponse(call: Call<TotalPost>, response: Response<TotalPost>) {
                val result = response.body() as TotalPost
                val totalPostRecyclerview = TotalPostRecyclerview(result)
                binding.totalPostRv.adapter = totalPostRecyclerview
                totalPostRecyclerview.setItemClickListener(object :
                    TotalPostRecyclerview.OnItemClickListener {
                    override fun onClick(v: View, position: Int, data: Result) {
                        startActivity(
                            Intent(
                                context,
                                DetailPostActivity::class.java
                            ).apply {
                                putExtra("data", data.id)
                                putExtra("date", data.createdAt)
                            }
                        )
                    }
                })
            }

            override fun onFailure(call: Call<TotalPost>, t: Throwable) {
                showCustomToast(t.message.toString())
            }
        })
    }
}