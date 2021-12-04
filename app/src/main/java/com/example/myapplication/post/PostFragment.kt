package com.example.myapplication.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentPostBinding
import com.example.myapplication.post.mypost.MyPostFragment
import com.example.myapplication.post.total.TotalPostFragment
import com.google.android.material.tabs.TabLayoutMediator


class PostFragment : BaseFragment<FragmentPostBinding>(FragmentPostBinding::bind, R.layout.fragment_post) {

	val tabTextList = arrayListOf("포스트", "나의 포스트")

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.postVp.adapter = ScreenSlidePagerAdapter(requireActivity())
		binding.postVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

		TabLayoutMediator(binding.postTab, binding.postVp) { tab, position ->
			tab.text = tabTextList[position]
		}.attach()
	}

	private inner class ScreenSlidePagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm){
		override fun getItemCount(): Int {
			return tabTextList.size
		}

		override fun createFragment(position: Int): Fragment {
			return when(position){
				0 -> TotalPostFragment()
				else -> MyPostFragment()
			}
		}
	}
}