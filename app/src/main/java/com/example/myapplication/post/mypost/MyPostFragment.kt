package com.example.myapplication.post.mypost

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentMyPostBinding
import com.example.myapplication.post.mypost.adapter.MyPostRecyclerView
import com.example.myapplication.post.mypost.models.MyPost
import com.example.myapplication.post.mypost.models.MyPostRetrofitInterface
import com.example.myapplication.post.write.WriteActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyPostFragment :
    BaseFragment<FragmentMyPostBinding>(FragmentMyPostBinding::bind, R.layout.fragment_my_post) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mypostRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.mypostRv.setHasFixedSize(true)

        // 플로팅 버튼 클릭 시
        binding.gotoWritePostFbtn.setOnClickListener {
            startActivity(Intent(requireContext(), WriteActivity::class.java))
        }

        getMyPost()
    }

    fun getMyPost() {
        val myPostRetrofitInterface =
            ApplicationClass.sRetrofit.create(MyPostRetrofitInterface::class.java)
        myPostRetrofitInterface.getMyPost().enqueue(object : Callback<MyPost> {
            override fun onResponse(call: Call<MyPost>, response: Response<MyPost>) {
                val result = response.body() as MyPost
                if (result.result.isEmpty()) {
                    binding.nothingMypostTv.visibility = View.VISIBLE
                } else {
                    binding.nothingMypostTv.visibility = View.GONE
                    binding.mypostRv.adapter = MyPostRecyclerView(result)
                }
            }

            override fun onFailure(call: Call<MyPost>, t: Throwable) {
                showCustomToast(t.message.toString())
            }
        })
    }
}