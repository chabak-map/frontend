package com.example.myapplication.join

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityJoinBinding
import com.example.myapplication.join.models.PostSignUpRequest
import com.example.myapplication.join.models.SignUpResponse
import com.example.myapplication.join.sms.PostSmsRequest
import com.example.myapplication.join.sms.SMSResponse
import com.example.myapplication.join.sms.SMSRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class JoinActivity : BaseActivity<ActivityJoinBinding>(ActivityJoinBinding::inflate),
	View.OnClickListener, JoinActivityView {
	val emailValidation =
		"^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
	val passwdValidation = "^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{8,15}\$"

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		binding.joinName.setOnClickListener(this)
		binding.joinId.setOnClickListener(this)
		binding.joinPw.setOnClickListener(this)
		binding.joinCheckPw.setOnClickListener(this)

		//뒤로가기 버튼 클릭
		binding.ivJoinBack.setOnClickListener {
			finish()
		}

		// 인증번호 버튼
		binding.ivPhoneNumBtn.setOnClickListener {
			val phoneNumber = binding.etPhoneNum.text.toString()
			val smsRequest = PostSmsRequest(
				phoneNumber = phoneNumber
			)
			tryPostSMS(smsRequest)
		}

		//가입하기 버튼 클릭 시
		binding.joinBtn.setOnClickListener {
			if (binding.joinName.text.toString() == "") {
				showCustomToast("이름을 입력해주세요")
			} else if (binding.joinPw.text.toString() == "") {
				showCustomToast("패스워드를 입력해주세요.")
			} else if (binding.joinId.text.toString() == "") {
				showCustomToast("아이디를 입력해주세요.")
			} else if (binding.joinCheckPw.text.toString() == "") {
				showCustomToast("패스워드를 입력해주세요")
			} else {
				val name = binding.joinName.text.toString()
				val email = binding.joinId.text.toString()
				val password = binding.joinPw.toString()
				val phoneNumber = binding.etPhoneNum.text.toString()
				val postRequest = PostSignUpRequest(
					email = email, password = password, nickname = name,
					phoneNumber = phoneNumber
				)
				JoinService(this).tryPostSignUp(postRequest)
				finish()
			}
		}

		binding.joinId.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

			}

			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                checkEmail()
			}

			override fun afterTextChanged(s: Editable?) {

			}
		})
		binding.joinPw.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

			}

			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                checkPasswd()
			}

			override fun afterTextChanged(s: Editable?) {

			}
		})

		binding.joinCheckPw.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

			}

			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (binding.joinPw.text.toString() == binding.joinCheckPw.text.toString()) {
//                    binding.errorCheckPw.visibility = View.GONE
//                } else {
//                    binding.errorCheckPw.visibility = View.VISIBLE
//                }
			}

			override fun afterTextChanged(s: Editable?) {

			}
		})
	}

	fun tryPostSMS(smsRequest: PostSmsRequest){
		val smsRetrofitInterface = ApplicationClass.sRetrofit.create(SMSRetrofitInterface::class.java)
		smsRetrofitInterface.postSMS(smsRequest).enqueue(object : Callback<SMSResponse> {

			override fun onResponse(call: Call<SMSResponse>, response: Response<SMSResponse>) {
				val sms = response.body() as SMSResponse
				showCustomToast("${sms.result}")
			}

			override fun onFailure(call: Call<SMSResponse>, t: Throwable) {
				showCustomToast(t.message ?: "오류")
			}
		})
	}

	override fun onPostSignUpSuccess(response: SignUpResponse) {
		showCustomToast("${response.result}")
	}

	override fun onPostSignUpFailure(message: String) {
		showCustomToast("오류  : $message")
	}

	override fun onClick(v: View?) {
		when (v?.id) {
			binding.joinName.id -> {
				binding.joinName.setBackgroundResource(R.drawable.join_click)
				binding.joinId.setBackgroundResource(R.drawable.join_nonclick)
				binding.joinPw.setBackgroundResource(R.drawable.join_nonclick)
				binding.joinCheckPw.setBackgroundResource(R.drawable.join_nonclick)
			}
			binding.joinId.id -> {
				binding.joinName.setBackgroundResource(R.drawable.join_nonclick)
				binding.joinId.setBackgroundResource(R.drawable.join_click)
				binding.joinPw.setBackgroundResource(R.drawable.join_nonclick)
				binding.joinCheckPw.setBackgroundResource(R.drawable.join_nonclick)
			}
			binding.joinPw.id -> {
				binding.joinName.setBackgroundResource(R.drawable.join_nonclick)
				binding.joinId.setBackgroundResource(R.drawable.join_nonclick)
				binding.joinPw.setBackgroundResource(R.drawable.join_click)
				binding.joinCheckPw.setBackgroundResource(R.drawable.join_nonclick)
			}

			binding.joinCheckPw.id -> {
				binding.joinName.setBackgroundResource(R.drawable.join_nonclick)
				binding.joinId.setBackgroundResource(R.drawable.join_nonclick)
				binding.joinPw.setBackgroundResource(R.drawable.join_nonclick)
				binding.joinCheckPw.setBackgroundResource(R.drawable.join_click)
			}
		}
	}

//    fun checkEmail(): Boolean {
//        var email = binding.joinId.text.toString().trim() // trim: 문자열의 양쪽 공백을 없애주는 것
//        val pattern = Pattern.matches(emailValidation, email)
//        if (pattern) {
//            binding.joinId.setTextColor(R.color.black.toInt())
//            binding.errorEmail.visibility = View.GONE
//            return true
//        } else {
//            binding.joinId.setTextColor(-65536)
//            binding.errorEmail.visibility = View.VISIBLE
//            return false
//        }
//    }


//    fun checkPasswd(): Boolean {
//        var passwd = binding.joinPw.text.toString().trim()
//        val pattern = Pattern.matches(passwdValidation, passwd)
//        if (pattern) {
//            binding.errorPw.visibility = View.GONE
//            return true
//        } else {
//            binding.errorPw.visibility = View.VISIBLE
//            return false
//        }
//    }
}