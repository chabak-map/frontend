package com.example.myapplication.map.detail.review.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.map.detail.review.models.MapReview
import com.example.myapplication.map.detail.review.models.Result

class MapReviewRecyclerView(val reviewList : MapReview) :
RecyclerView.Adapter<MapReviewRecyclerView.CustomHolder>(){
	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): MapReviewRecyclerView.CustomHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.map_detail_comment_item, parent, false)
		return CustomHolder(view)
	}

	override fun onBindViewHolder(holder: MapReviewRecyclerView.CustomHolder, position: Int) {
		holder.bindItems(reviewList.result[position])
	}

	override fun getItemCount(): Int {
		return reviewList.result.size
	}

	inner class CustomHolder(view : View) : RecyclerView.ViewHolder(view){
		fun bindItems(data : Result){
			itemView.findViewById<TextView>(R.id.map_detail_id_tv).text = data.name
			itemView.findViewById<TextView>(R.id.map_detail_content_tv).text = data.content
			itemView.findViewById<TextView>(R.id.map_detail_date_tv).text = data.writingDate
		}
	}
}