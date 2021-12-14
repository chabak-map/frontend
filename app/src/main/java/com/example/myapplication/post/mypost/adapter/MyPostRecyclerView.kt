package com.example.myapplication.post.mypost.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.glide.GlideApp
import com.example.myapplication.post.mypost.models.MyPost
import com.example.myapplication.post.mypost.models.Result

class MyPostRecyclerView(val mypostList : MyPost) :
RecyclerView.Adapter<MyPostRecyclerView.CustomHolder>(){
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

	inner class CustomHolder(view: View) : RecyclerView.ViewHolder(view){
		val img : ImageView = view.findViewById(R.id.my_post_img)
		fun bindItems(data : Result){
			itemView.findViewById<TextView>(R.id.my_post_title_tv).text = data.title
			itemView.findViewById<TextView>(R.id.my_post_writer_tv).text =data.nickname
			GlideApp.with(itemView).load(data.imageUrl).into(img)
			itemView.findViewById<TextView>(R.id.my_post_speech_cnt_tv).text = data.commentCount.toString()
		}
	}
}