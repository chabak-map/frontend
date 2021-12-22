//package com.example.myapplication.home
//
//import android.content.Context
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.myapplication.databinding.MainRankItemBinding
//import com.example.myapplication.home.detail.Rank
//
//class RankAdapter(
//    private val context: Context,
//    val rankList: Rank) :
//    RecyclerView.Adapter<RankAdapter.ViewHolder>() {
//
//    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//    lateinit var binding: MainRankItemBinding
//
//    // 뷰홀더가 생성 되었을 때
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        binding = MainRankItemBinding.inflate(inflater, parent, false)
//        return ViewHolder(binding)
//    }
//
//    // 뷰와 뷰홀더가 묶였을 때
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(rankList)
//    }
//
//    // 목록의 수
//    override fun getItemCount(): Int {
//        return this.rankList.size
//    }
//
//
//    class ViewHolder(private val binding: MainRankItemBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        private val rankNum = binding.tvRankNumber
//        private val rankImg = binding.ivRankImg
//        private val rankName = binding.tvRankName
//        private val rankLocation = binding.tvRankLocation
//
//        // 데이터와 뷰를 연결
//        fun bind(rankListData: RankListData) {
//            rankNum.text = rankListData.rankNumber
//            rankImg.setImageResource(rankListData.rankImage)
//            rankName.text = rankListData.rankName
//            rankLocation.text = rankListData.rankLocation
//        }
//    }
//
//}