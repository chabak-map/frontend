package com.example.myapplication.find

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentFindPwBinding

class FindPwFragment :
    BaseFragment<FragmentFindPwBinding>(FragmentFindPwBinding::bind, R.layout.fragment_find_pw) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 확인 버튼 클릭
        binding.btnFindPw.setOnClickListener {
            startActivity(Intent(requireContext(), FindPwEditActivity::class.java))
        }
    }

}