package com.example.myapplication.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.MainCloseItemBinding
import com.example.myapplication.databinding.MainRankItemBinding

class CloseAdapter(
    private val context: Context,
    val closeList: ArrayList<CloseListData>
) :
    RecyclerView.Adapter<CloseAdapter.ViewHolder>() {

    private val inflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding: MainCloseItemBinding

    // 뷰홀더가 생성 되었을 때
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = MainCloseItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    // 뷰와 뷰홀더가 묶였을 때
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.closeList[position])
    }

    // 목록의 수
    override fun getItemCount(): Int {
        return this.closeList.size
    }

    class ViewHolder(private val binding: MainCloseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val closeImg = binding.ivCloseBackground
        private val closeDistance = binding.tvCloseDistance
        private val closeName = binding.tvCloseName
        private val closeLocation = binding.tvCloseLocation
        private val closeBook = binding.ivCloseBookmark

        // 데이터와 뷰를 연결
        fun bind(closeListData: CloseListData) {
            closeImg.setImageResource(closeListData.closeImage)
            closeDistance.text = closeListData.closeDistance.toString()
            closeName.text = closeListData.closeName
            closeLocation.text = closeListData.closeLocation
            closeBook.setImageResource(closeListData.closeBookMark)
        }
    }

}