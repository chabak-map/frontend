package com.example.myapplication.find

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityFindBinding
import com.google.android.material.tabs.TabLayoutMediator

class FindActivity : BaseActivity<ActivityFindBinding>(ActivityFindBinding::inflate){

    val tabTextList = arrayListOf("아이디 찾기", "비밀번호 찾기")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 뒤로가기 버튼 클릭 시
        binding.ivFindBack.setOnClickListener {
            finish()
        }

        binding.findFrame.adapter = ScreenSlidePagerAdapter(this)
        binding.findFrame.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(binding.tabLayoutFind, binding.findFrame) { tab, position ->
            tab.text = tabTextList[position]
        }.attach()
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
        override fun getItemCount(): Int {
            return tabTextList.size
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> FindIdFragment()
                else -> FindPwFragment()
            }
        }
    }
}

