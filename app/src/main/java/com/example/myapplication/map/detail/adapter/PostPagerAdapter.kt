package com.example.myapplication.map.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.example.myapplication.R
import com.example.myapplication.glide.GlideApp
import com.example.myapplication.map.detail.models.DetailPost
import com.example.myapplication.map.detail.models.Result
import kotlinx.android.synthetic.main.detail_post_item.view.*

class PostPagerAdapter(private val context:Context, private val items : ArrayList<String>) :
	RecyclerView.Adapter<PostPagerAdapter.PagerViewHolder>() {

	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): PostPagerAdapter.PagerViewHolder = PagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.detail_post_item, parent, false))


	override fun onBindViewHolder(holder: PostPagerAdapter.PagerViewHolder, position: Int) {
		GlideApp.with(context).load(items[position]).centerCrop().into(holder.img)
	}

	override fun getItemCount(): Int {
		return items.size
	}

	inner class PagerViewHolder(view : View) : RecyclerView.ViewHolder(view){
		val img : ImageView = view.findViewById(R.id.detail_post)

	}
}