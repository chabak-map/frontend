package com.example.myapplication.map.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.map.models.RadiusPlace

class RadiusPlaceRecyclerView(val placeList : RadiusPlace) :
RecyclerView.Adapter<RadiusPlaceRecyclerView.CustomViewHolder>(){
	override fun onBindViewHolder(holder: RadiusPlaceRecyclerView.CustomViewHolder, position: Int) {
		TODO("Not yet implemented")
	}

	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): RadiusPlaceRecyclerView.CustomViewHolder {
		TODO("Not yet implemented")
	}

	override fun getItemCount(): Int {
		TODO("Not yet implemented")
	}

	class CustomViewHolder(view : View) : RecyclerView.ViewHolder(view){

		fun bindItems(data : RadiusPlace){
			itemView.findViewById<TextView>(R.id.radius_place_distance_tv).text = data.result.dis
		}
	}
}