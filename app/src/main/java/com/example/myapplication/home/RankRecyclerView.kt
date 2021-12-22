package com.example.myapplication.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.MainRankItemBinding
import com.example.myapplication.glide.GlideApp
import com.example.myapplication.home.detail.Rank
import com.example.myapplication.home.detail.Result

class RankRecyclerView(val rankList: Rank) :
	RecyclerView.Adapter<RankRecyclerView.CustomHolder>() {

	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): RankRecyclerView.CustomHolder {
		val view =
			LayoutInflater.from(parent.context).inflate(R.layout.main_rank_item, parent, false)
		return CustomHolder(view)
	}

	override fun onBindViewHolder(holder: RankRecyclerView.CustomHolder, position: Int) {
		holder.itembind(rankList.result[position])
	}

	override fun getItemCount(): Int {
		return rankList.result.size
	}

	inner class CustomHolder(view: View) : RecyclerView.ViewHolder(view) {
		var rankImg: ImageView = view.findViewById(R.id.iv_rank_img)
		fun itembind(data: Result) {

			itemView.findViewById<CardView>(R.id.cv_rank_img_background).visibility = View.VISIBLE
			GlideApp.with(itemView).load(data.placeImageUrl).into(rankImg)
			itemView.findViewById<TextView>(R.id.tv_rank_location).text = data.address
			itemView.findViewById<TextView>(R.id.tv_rank_name).text = data.name
		}
	}
}