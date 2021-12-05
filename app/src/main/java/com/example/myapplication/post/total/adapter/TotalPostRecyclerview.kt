package com.example.myapplication.post.total.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.post.total.models.Result
import com.example.myapplication.post.total.models.TotalPost

class TotalPostRecyclerview(val postList: TotalPost) :
RecyclerView.Adapter<TotalPostRecyclerview.CustomHolder>(){

	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): TotalPostRecyclerview.CustomHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.total_post_item, parent, false)
		return CustomHolder(view)
	}

	override fun onBindViewHolder(holder: TotalPostRecyclerview.CustomHolder, position: Int) {
		holder.bindItems(postList.result[position])
	}

	override fun getItemCount(): Int {
		return postList.result.size
	}

	inner class CustomHolder(view : View) : RecyclerView.ViewHolder(view){
		val img : ImageView = view.findViewById(R.id.total_post_img)
		fun bindItems(data : Result){
			itemView.findViewById<TextView>(R.id.total_post_title_tv).text = data.title
			itemView.findViewById<TextView>(R.id.total_post_writer_tv).text = data.nickname
			itemView.findViewById<TextView>(R.id.total_post_speech_cnt_tv).text = data.commentCount.toString()
			Glide.with(itemView).load(data.imageUrl).into(img)

		}
	}
}