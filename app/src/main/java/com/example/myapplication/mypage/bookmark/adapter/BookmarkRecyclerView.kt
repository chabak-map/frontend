package com.example.myapplication.mypage.bookmark.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.glide.GlideApp
import com.example.myapplication.mypage.bookmark.models.Bookmarks
import com.example.myapplication.mypage.bookmark.models.Result
import de.hdodenhof.circleimageview.CircleImageView

class BookmarkRecyclerView(val bookmark : Bookmarks)
	: RecyclerView.Adapter<BookmarkRecyclerView.CustomHolder>(){
	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): BookmarkRecyclerView.CustomHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bookmark, parent, false)
		return CustomHolder(view)
	}

	override fun onBindViewHolder(holder: BookmarkRecyclerView.CustomHolder, position: Int) {
		holder.bindItems(bookmark.result[position])
	}

	override fun getItemCount(): Int {
		return bookmark.result.size
	}

	inner class CustomHolder(view : View) : RecyclerView.ViewHolder(view){
		val placeImg = itemView.findViewById<ImageView>(R.id.bookmark_place_img)
		fun bindItems(data : Result){
			itemView.findViewById<TextView>(R.id.bookmark_title_tv).text = data.name
			itemView.findViewById<TextView>(R.id.bookmark_address_tv).text = data.address
			GlideApp.with(itemView).load(data.imageUrl).into(placeImg)
		}
	}
}