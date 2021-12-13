package com.example.myapplication.map.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.glide.GlideApp
import com.example.myapplication.map.models.RadiusPlace
import com.example.myapplication.map.models.RadiusPlaceName
import com.example.myapplication.map.models.RadiusPlaceRetrofitInterface
import com.example.myapplication.map.models.Result
import retrofit2.Call
import retrofit2.Callback
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
				data.distance.toString() + "km"
			getPlaceId(data.placeId)
			val pos = adapterPosition
			if(pos!= RecyclerView.NO_POSITION){
				itemView.setOnClickListener {
					itemClickListener.onClick(itemView, pos, data)
				}
			}
		}
		fun getPlaceId(placeId : Int){
			var placeRetrofitInterface = ApplicationClass.sRetrofit.create(RadiusPlaceRetrofitInterface::class.java)
			placeRetrofitInterface.getPlaceName(placeId).enqueue(object : Callback<RadiusPlaceName>{
				override fun onResponse(
					call: Call<RadiusPlaceName>,
					response: Response<RadiusPlaceName>
				) {
					val result = response.body() as RadiusPlaceName
					itemView.findViewById<TextView>(R.id.radius_place_tv).text = result.result.name
//					Glide.with(itemView).load(result.result.placeImageUrls[0]).into(place_img)
				}

				override fun onFailure(call: Call<RadiusPlaceName>, t: Throwable) {
					Log.d("NameError", "${t.message}")
				}
			})
		}
	}
}
