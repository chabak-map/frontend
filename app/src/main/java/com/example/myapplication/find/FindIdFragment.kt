package com.example.myapplication.find

import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentFindIdBinding

class FindIdFragment :
    BaseFragment<FragmentFindIdBinding>(FragmentFindIdBinding::bind, R.layout.fragment_find_id) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.joinBtn.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.frame_id, FindIdResultFragment())
//                .addToBackStack(null)
                .commit()
        }


    }

}