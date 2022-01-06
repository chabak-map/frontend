package com.example.myapplication.find

import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentFindIdBinding
import com.example.myapplication.find.models.*

class FindIdFragment :
	BaseFragment<FragmentFindIdBinding>(FragmentFindIdBinding::bind, R.layout.fragment_find_id), FindEmailView {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		// 확인 버튼 클릭시
		binding.joinBtn.setOnClickListener {
			childFragmentManager.beginTransaction()
				.replace(R.id.frame_id, FindIdResultFragment())
                .addToBackStack(null)
				.commit()
		}

		// 인증번호 전송 버튼 클릭시
		binding.postCheckNumberBtn.setOnClickListener {
			val phoneNum = binding.etFindPhoneNum
			val findRequest = FindEmailRequest(phoneNumber = phoneNum.text.toString())
			FindEmailService(this).tryFindEmail(findRequest)
		}
	}

	override fun onPostSignUpSuccess(response: FindEmailResponse) {
		showCustomToast(response.message.toString())
	}

	override fun onPostSignUpFailure(message: String) {
		showCustomToast(message)
	}
}