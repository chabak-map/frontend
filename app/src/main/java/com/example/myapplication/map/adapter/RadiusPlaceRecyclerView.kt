package com.example.myapplication.map.adapter

import android.annotation.SuppressLint
import android.app.FragmentManager
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.databinding.FragmentDetailPostBinding
import com.example.myapplication.map.MapFragment
import com.example.myapplication.map.detail.DetailPostFragment
import com.example.myapplication.map.models.RadiusPlace
import com.example.myapplication.map.models.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.coroutineContext

class RadiusPlaceRecyclerView(val placeList : RadiusPlace) :
RecyclerView.Adapter<RadiusPlaceRecyclerView.CustomViewHolder>(){
	override fun onBindViewHolder(holder: RadiusPlaceRecyclerView.CustomViewHolder, position: Int) {
		holder.itemView.setOnClickListener {
			itemClickListener.onClick(it, position)
		}
		holder.bindItems(placeList.result[position])
	}

	interface OnItemClickListener {
		fun onClick(v : View, position: Int)
	}
	// 외부에서 클릭 시 이벤트 설정
	fun setItemClickListener(onItemClickListener : OnItemClickListener){
		this.itemClickListener = onItemClickListener
	}
	
	private lateinit var itemClickListener : OnItemClickListener
	
	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): RadiusPlaceRecyclerView.CustomViewHolder {
		val view =LayoutInflater.from(parent.context).inflate(R.layout.radius_place_item, parent, false)
		return CustomViewHolder(view)
	}

	override fun getItemCount(): Int {
		return placeList.result.size
	}

	class CustomViewHolder(view : View) : RecyclerView.ViewHolder(view){
		private val place_img : ImageView = view.findViewById(R.id.radius_place_img)
		@SuppressLint("SetTextI18n")
		fun bindItems(data : Result){
			itemView.findViewById<TextView>(R.id.radius_place_distance_tv).text = data.distance.toString() + "m"
		}
	}
}