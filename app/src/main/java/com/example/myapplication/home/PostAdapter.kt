package com.example.myapplication.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.MainPostItemBinding

class PostAdapter(
    private val context: Context,
    val postList: ArrayList<PostListData>
) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private val inflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding: MainPostItemBinding

    // 뷰홀더가 생성 되었을 때
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = MainPostItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    // 뷰와 뷰홀더가 묶였을 때
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.postList[position])
    }

    // 목록의 수
    override fun getItemCount(): Int {
        return this.postList.size
    }


    class ViewHolder(private val binding: MainPostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val postImg = binding.ivPostBackground
        private val postName = binding.tvPostText
        private val postUser = binding.tvPostUser

        // 데이터와 뷰를 연결
        fun bind(postListData: PostListData) {
            postImg.setImageResource(postListData.postImage)
            postName.text = postListData.postName
            postUser.text = postListData.postUser
        }
    }

}