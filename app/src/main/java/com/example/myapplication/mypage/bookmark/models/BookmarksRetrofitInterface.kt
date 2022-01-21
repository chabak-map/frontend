package com.example.myapplication.mypage.bookmark.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BookmarksRetrofitInterface {
	@GET("/bookmarks")
	fun getBookmarks() : Call<Bookmarks>

	@POST("/bookmarks/{placeId}")
	fun tryBookmarks(@Path("placeId") placeId : Int) : Call<Bookmarks>
}