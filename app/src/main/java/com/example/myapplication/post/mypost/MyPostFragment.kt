package com.example.myapplication.post.mypost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentMyPostBinding


class MyPostFragment : BaseFragment<FragmentMyPostBinding>(FragmentMyPostBinding::bind, R.layout.fragment_my_post) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}

}