package com.example.myapplication.map.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.map.detail.models.CommentRes
import com.example.myapplication.map.detail.models.MapDetail

class MapCommentRecyclerView(val commentList : MapDetail) :
RecyclerView.Adapter<MapCommentRecyclerView.CustomHolder>(){

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.map_detail_comment_item, parent, false)
		return CustomHolder(view)
	}

	override fun onBindViewHolder(holder: CustomHolder, position: Int) {
		holder.bindItems(commentList.result.commentResList[position]) // 포지션하려면 bindItems가 List형태로 묶여있는 걸 해야함
	}

	override fun getItemCount(): Int {
		return commentList.result.commentResList.size
	}

	inner class CustomHolder(view : View) : RecyclerView.ViewHolder(view){
		fun bindItems(data : CommentRes){
			itemView.findViewById<TextView>(R.id.map_detail_id_tv).text = data.name
			itemView.findViewById<TextView>(R.id.map_detail_content_tv).text = data.content
			itemView.findViewById<TextView>(R.id.map_detail_date_tv).text = data.writingDate
		}
	}
}