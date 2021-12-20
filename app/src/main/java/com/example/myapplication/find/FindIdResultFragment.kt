package com.example.myapplication.find

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentFindIdResultBinding
import com.example.myapplication.login.LoginActivity

class FindIdResultFragment : BaseFragment<FragmentFindIdResultBinding>(
    FragmentFindIdResultBinding::bind,
    R.layout.fragment_find_id_result
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnIdFindLogin.setOnClickListener {

            // 로그인 액티비티 열기
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }
    }
}
