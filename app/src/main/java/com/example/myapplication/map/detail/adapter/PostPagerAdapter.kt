package com.example.myapplication.map.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.map.detail.models.DetailPost
import kotlinx.android.synthetic.main.detail_post_item.view.*

class PostPagerAdapter(private val context: Context, postList : ArrayList<String>) :
	RecyclerView.Adapter<PostPagerAdapter.PagerViewHolder>() {

	var item = postList

	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): PostPagerAdapter.PagerViewHolder = PagerViewHolder(parent)

	override fun onBindViewHolder(holder: PostPagerAdapter.PagerViewHolder, position: Int) {
		Glide.with(context).load(item[position]).into(holder.detailPost)
	}

	override fun getItemCount(): Int {
		return item.size
	}

	inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
		LayoutInflater.from(parent.context).inflate(R.layout.detail_post_item, parent, false)){

		val detailPost = itemView.detail_post
	}
}