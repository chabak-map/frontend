package com.example.myapplication.post.total.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.glide.GlideApp
import com.example.myapplication.post.total.models.Result
import com.example.myapplication.post.total.models.TotalPost
import okio.utf8Size

class TotalPostRecyclerview(val postList: TotalPost) :
RecyclerView.Adapter<TotalPostRecyclerview.CustomHolder>(){

	interface OnItemClickListener{
		fun onClick(v: View, position: Int, data: Result)
	}

	fun setItemClickListener(onItemClickListener : OnItemClickListener){
		this.itemClickListener = onItemClickListener
	}

	private lateinit var itemClickListener : OnItemClickListener
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
			GlideApp.with(itemView).load(data.imageUrl).into(img)
			if(data.imageUrl.isEmpty()){
				itemView.findViewById<ImageView>(R.id.total_post_img).visibility = View.GONE
				itemView.findViewById<ImageView>(R.id.total_post_multi_pic_img).visibility = View.GONE
				itemView.findViewById<CardView>(R.id.total_post_background_cv).visibility = View.GONE
			}
			else if (data.imageUrl.split(",").size == 1){
				itemView.findViewById<ImageView>(R.id.total_post_multi_pic_img).visibility = View.GONE
			}
			val pos = adapterPosition
			if(pos!=RecyclerView.NO_POSITION){
				itemView.setOnClickListener {
					itemClickListener.onClick(itemView, pos, data)
				}
			}
		}
	}
}