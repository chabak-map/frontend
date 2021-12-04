package com.example.myapplication.find

import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityFindBinding
import com.google.android.material.tabs.TabLayout

class FindActivity : BaseActivity<ActivityFindBinding>(ActivityFindBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 뒤로가기 버튼 클릭 시
        binding.ivFindBack.setOnClickListener {
            finish()
        }

        binding.tabLayoutFind.selectedTabPosition

        binding.tabLayoutFind.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {

                    // 아이디 찾기 프레그먼트
                    0 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.find_frame, FindIdFragment())
                            .commit()
                    }
                    // 비밀번호 찾기 프레그먼트
                    1 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.find_frame, FindPwFragment())
                            .commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

    }

}

