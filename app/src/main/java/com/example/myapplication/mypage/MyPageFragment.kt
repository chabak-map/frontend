package com.example.myapplication.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentMyPageBinding
import com.example.myapplication.mypage.bookmark.UserBookmarkFragment
import com.example.myapplication.mypage.profile.UserProfileFragment

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.gotoUserPageTv.setOnClickListener {
			fragmentManager?.beginTransaction()?.replace(R.id.main_frame, UserProfileFragment())?.commit()
		}
		binding.gotoUserBookmarkTv.setOnClickListener {
			fragmentManager?.beginTransaction()?.replace(R.id.main_frame, UserBookmarkFragment())?.commit()
		}
	}
}