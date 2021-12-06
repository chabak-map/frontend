package com.example.myapplication.mypage.profile

import com.example.myapplication.mypage.profile.models.ProfileResponse

interface ProfileActivityView {
	fun profileSuccess(response: ProfileResponse)

	fun profileFailed(message: String)
}