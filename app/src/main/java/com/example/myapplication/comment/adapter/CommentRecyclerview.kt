package com.example.myapplication.comment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.comment.models.Comments
import com.example.myapplication.comment.models.Result

class CommentRecyclerview (val commentList : Comments) :
RecyclerView.Adapter<CommentRecyclerview.ViewHolder>(){
	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): CommentRecyclerview.ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(holder: CommentRecyclerview.ViewHolder, position: Int) {
		holder.bindItems(data.result[position])
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