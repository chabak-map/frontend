package com.example.myapplication.map.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.map.models.RadiusPlace
import com.example.myapplication.map.models.Result
import retrofit2.Response
import kotlin.coroutines.coroutineContext

class RadiusPlaceRecyclerView(val placeList: RadiusPlace) :
	RecyclerView.Adapter<RadiusPlaceRecyclerView.CustomViewHolder>() {

	interface OnItemClickListener {
		fun onClick(v: View, position: Int, data: Result)
	}

	// 외부에서 클릭 시 이벤트 설정
	fun setItemClickListener(onItemClickListener: OnItemClickListener) {
		this.itemClickListener = onItemClickListener
	}

	private lateinit var itemClickListener : OnItemClickListener

	override fun onBindViewHolder(holder: RadiusPlaceRecyclerView.CustomViewHolder, position: Int) {
		holder.bindItems(placeList.result[position])
	}



	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): RadiusPlaceRecyclerView.CustomViewHolder {
		val view =
			LayoutInflater.from(parent.context).inflate(R.layout.radius_place_item, parent, false)
		return CustomViewHolder(view)
	}

	override fun getItemCount(): Int {
		return placeList.result.size
	}

	inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		private val place_img: ImageView = view.findViewById(R.id.radius_place_img)
		@SuppressLint("SetTextI18n")
		fun bindItems(data: Result) {
			itemView.findViewById<TextView>(R.id.radius_place_distance_tv).text =
				data.distance.toString() + "m"

			val pos = adapterPosition
			if(pos!= RecyclerView.NO_POSITION){
				itemView.setOnClickListener {
					itemClickListener.onClick(itemView, pos, data)
				}
			}
		}
	}
}
