package com.example.myapplication.map.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.map.models.RadiusPlace
import com.example.myapplication.map.models.Result
import com.example.myapplication.post.place.models.Place
import com.example.myapplication.post.place.models.PlaceRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RadiusPlaceRecyclerView(val placeList : RadiusPlace) :
RecyclerView.Adapter<RadiusPlaceRecyclerView.CustomViewHolder>(){
	override fun onBindViewHolder(holder: RadiusPlaceRecyclerView.CustomViewHolder, position: Int) {
		holder.bindItems(placeList.result[position])
	}

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
		fun bindItems(data : Result){
			itemView.findViewById<TextView>(R.id.radius_place_distance_tv).text = data.distance.toString() + "m"
			itemView.findViewById<TextView>(R.id.radius_place_tv).text = data.placeId.toString()
			tryGetPlaceId(data.placeId)
		}
		fun tryGetPlaceId(placeId : Int){
			val placeRetrofitInterface = ApplicationClass.sRetrofit.create(PlaceRetrofitInterface::class.java)
			placeRetrofitInterface.getPlace(placeId).enqueue(object : Callback<Place> {
				override fun onResponse(call: Call<Place>, response: Response<Place>) {
					val result = response.body() as Place

				}

				override fun onFailure(call: Call<Place>, t: Throwable) {
					Log.d("error","${t.message}")
				}
			})
		}
	}

}