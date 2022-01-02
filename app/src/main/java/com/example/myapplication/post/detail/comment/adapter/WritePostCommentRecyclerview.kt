package com.example.myapplication.post.detail.comment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.post.detail.comment.models.Comments
import com.example.myapplication.post.detail.comment.models.Result

class WritePostCommentRecyclerview (val commentList : Comments) :
RecyclerView.Adapter<WritePostCommentRecyclerview.ViewHolder>(){
	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bindItems(commentList.result[position])
	}

	override fun getItemCount(): Int {
		return commentList.result.size
	}

	inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
		fun bindItems(data : Result){
			itemView.findViewById<TextView>(R.id.comment_id_tv).text = data.name
			itemView.findViewById<TextView>(R.id.comment_comment_tv).text = data.content
			itemView.findViewById<TextView>(R.id.comment_date_tv).text = data.writingDate
		}
	}
}