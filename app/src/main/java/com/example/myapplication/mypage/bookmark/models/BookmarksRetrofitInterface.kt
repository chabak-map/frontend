package com.example.myapplication.mypage.bookmark.models

import retrofit2.Call
import retrofit2.http.GET

interface BookmarksRetrofitInterface {
	@GET("/bookmarks")
	fun getBookmarks() : Call<Bookmarks>
}