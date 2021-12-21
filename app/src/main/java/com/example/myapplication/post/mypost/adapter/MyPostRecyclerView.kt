package com.example.myapplication.post.mypost.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.glide.GlideApp
import com.example.myapplication.post.mypost.models.MyPost
import com.example.myapplication.post.mypost.models.Result
import com.example.myapplication.post.tag.models.PostTag
import com.example.myapplication.post.tag.models.PostTagRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPostRecyclerView(val mypostList: MyPost) :
	RecyclerView.Adapter<MyPostRecyclerView.CustomHolder>() {
	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): MyPostRecyclerView.CustomHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.mypost_item, parent, false)
		return CustomHolder(view)
	}

	override fun onBindViewHolder(holder: MyPostRecyclerView.CustomHolder, position: Int) {
		holder.bindItems(mypostList.result[position])
	}

	override fun getItemCount(): Int {
		return mypostList.result.size
	}

	inner class CustomHolder(view: View) : RecyclerView.ViewHolder(view) {
		val img: ImageView = view.findViewById(R.id.my_post_img)
		fun bindItems(data: Result) {
			itemView.findViewById<TextView>(R.id.my_post_title_tv).text = data.title
			itemView.findViewById<TextView>(R.id.my_post_writer_tv).text = data.nickname
			itemView.findViewById<TextView>(R.id.my_post_speech_cnt_tv).text =
				data.commentCount.toString()
			itemView.findViewById<TextView>(R.id.my_post_content_tv).text = data.content
			itemView.findViewById<TextView>(R.id.my_post_date_tv).text = data.createdAt
			if (data.imageUrls.isNotEmpty()) {
				GlideApp.with(itemView).load(data.imageUrls[0]).into(img)
				if (data.imageUrls.size == 1) {
					itemView.findViewById<ImageView>(R.id.my_post_multi_pic_img).visibility =
						View.GONE
				} else {
					itemView.findViewById<ImageView>(R.id.my_post_multi_pic_img).visibility =
						View.VISIBLE
				}
			} else if (data.imageUrls.isEmpty()) {
				itemView.findViewById<ImageView>(R.id.my_post_img).visibility = View.GONE
				itemView.findViewById<ImageView>(R.id.my_post_multi_pic_img).visibility =
					View.GONE
				itemView.findViewById<CardView>(R.id.my_post_background_cv).visibility =
					View.GONE
			}

		}
	}
}