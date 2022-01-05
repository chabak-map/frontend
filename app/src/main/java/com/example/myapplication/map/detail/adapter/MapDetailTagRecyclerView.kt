package com.example.myapplication.map.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.map.detail.models.MapDetail
import com.example.myapplication.map.detail.models.Result

class MapDetailTagRecyclerView(var tagList: MapDetail) :
	RecyclerView.Adapter<MapDetailTagRecyclerView.CustomHolder>() {
	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): MapDetailTagRecyclerView.CustomHolder {
		val view =
			LayoutInflater.from(parent.context).inflate(R.layout.map_detail_tag_item, parent, false)
		return CustomHolder(view)
	}

	override fun onBindViewHolder(holder: MapDetailTagRecyclerView.CustomHolder, position: Int) {
		holder.bindItems(tagList.result)
	}

	override fun getItemCount(): Int {
		return tagList.result.tagNames.size
	}

	inner class CustomHolder(view: View) : RecyclerView.ViewHolder(view) {
		val tag = ArrayList<String>()
		fun bindItems(data: Result) {
			for(i in data.tagNames.indices){
				tag.add(data.tagNames[i].toString())
			}
			itemView.findViewById<TextView>(R.id.map_detail_tag_tv).text = "# " + tag[position]
		}
	}
}