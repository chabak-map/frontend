package com.example.myapplication.post.total.adapter

import android.media.Image
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.glide.GlideApp
import com.example.myapplication.post.tag.models.PostTag
import com.example.myapplication.post.tag.models.PostTagRetrofitInterface
import com.example.myapplication.post.total.models.Result
import com.example.myapplication.post.total.models.TotalPost
import okio.utf8Size
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TotalPostRecyclerview(val postList: TotalPost) :
	RecyclerView.Adapter<TotalPostRecyclerview.CustomHolder>() {

	interface OnItemClickListener {
		fun onClick(v: View, position: Int, data: Result)
	}

	// 외부에서 클릭 시 이벤트 설정
	fun setItemClickListener(onItemClickListener: OnItemClickListener) {
		this.itemClickListener = onItemClickListener
	}

	private lateinit var itemClickListener: OnItemClickListener
	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): TotalPostRecyclerview.CustomHolder {
		val view =
			LayoutInflater.from(parent.context).inflate(R.layout.total_post_item, parent, false)
		return CustomHolder(view)
	}

	override fun onBindViewHolder(holder: TotalPostRecyclerview.CustomHolder, position: Int) {
		holder.bindItems(postList.result[position])
	}

	override fun getItemCount(): Int {
		return postList.result.size
	}

	inner class CustomHolder(view: View) : RecyclerView.ViewHolder(view) {
		val img: ImageView = view.findViewById(R.id.total_post_img)
		fun bindItems(data: Result) {
			itemView.findViewById<TextView>(R.id.total_post_title_tv).text = data.title
			itemView.findViewById<TextView>(R.id.total_post_writer_tv).text = data.nickname
			itemView.findViewById<TextView>(R.id.total_post_speech_cnt_tv).text =
				data.commentCount.toString()
			itemView.findViewById<TextView>(R.id.total_post_date_tv).text = data.createdAt
			itemView.findViewById<TextView>(R.id.total_post_content_tv).text = data.content
			if (data.imageUrls.isNotEmpty()) {
				GlideApp.with(itemView).load(data.imageUrls[0]).into(img)
				if (data.imageUrls.size == 1) {
					itemView.findViewById<ImageView>(R.id.total_post_multi_pic_img).visibility =
						View.GONE
				} else {
					itemView.findViewById<ImageView>(R.id.total_post_multi_pic_img).visibility =
						View.VISIBLE
				}
			} else if (data.imageUrls.isEmpty()) {
				itemView.findViewById<ImageView>(R.id.total_post_img).visibility = View.GONE
				itemView.findViewById<ImageView>(R.id.total_post_multi_pic_img).visibility =
					View.GONE
				itemView.findViewById<CardView>(R.id.total_post_background_cv).visibility =
					View.GONE
			}
			val pos = adapterPosition
			if (pos != RecyclerView.NO_POSITION) {
				itemView.setOnClickListener {
					itemClickListener.onClick(itemView, pos, data)
				}
			}
			getPostTag(data.id)
		}

		val tag = ArrayList<String>()

		fun getPostTag(postId: Int) {
			val postTagRetrofitInterface = ApplicationClass.sRetrofit.create(
				PostTagRetrofitInterface::class.java
			)
			postTagRetrofitInterface.getPostTag(postId).enqueue(object : Callback<PostTag> {
				@RequiresApi(Build.VERSION_CODES.N)
				override fun onResponse(call: Call<PostTag>, response: Response<PostTag>) {
					val result = response.body() as PostTag
					println(result.result)
					for (element in result.result) {
						tag.add(element)
					}
				}

				override fun onFailure(call: Call<PostTag>, t: Throwable) {
					Log.d("PostTagError", t.message.toString())
				}
			})
		}
	}
}