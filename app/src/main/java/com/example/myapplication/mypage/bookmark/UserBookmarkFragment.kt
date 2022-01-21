package com.example.myapplication.mypage.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentUserBookmarkBinding
import com.example.myapplication.mypage.bookmark.adapter.BookmarkRecyclerView
import com.example.myapplication.mypage.bookmark.models.Bookmarks
import com.example.myapplication.mypage.bookmark.models.BookmarksRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserBookmarkFragment : BaseFragment<FragmentUserBookmarkBinding>(FragmentUserBookmarkBinding::bind, R.layout.fragment_user_bookmark) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.bookmarkCntTv.visibility = View.GONE
		binding.myBookmarkRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
		binding.myBookmarkRv.setHasFixedSize(true)
		getBookmark()
	}

	fun getBookmark(){
		val bookmarksRetrofitInterface = ApplicationClass.sRetrofit.create(BookmarksRetrofitInterface::class.java)
		bookmarksRetrofitInterface.getBookmarks().enqueue(object : Callback<Bookmarks>{
			override fun onResponse(call: Call<Bookmarks>, response: Response<Bookmarks>) {
				val result = response.body() as Bookmarks
				if (result.result.isEmpty()){
					binding.bookmarkCntTv.visibility = View.VISIBLE
				}
				binding.myBookmarkRv.adapter = BookmarkRecyclerView(result)
			}

			override fun onFailure(call: Call<Bookmarks>, t: Throwable) {
				showCustomToast(t.message.toString())
			}
		})
	}
}