package com.example.myapplication.home

import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.home.detail.Rank
import com.example.myapplication.home.detail.RankRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 차박지 랭킹
data class RankListData(
	val rankNumber: String,
	val rankImage: Int,
	val rankName: String,
	val rankLocation: String
)

// 차박지 포스트
data class PostListData(
	val postImage: Int,
	val postName: String,
	val postUser: String
)

// 가까운 차박지
data class CloseListData(
	val closeImage: Int,
	val closeDistance: Int,
	val closeName: String,
	val closeLocation: String,
	val closeBookMark: Int
)

class HomeFragment :
	BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

//	// 차박지 랭킹 리스트
//	private var rankList = ArrayList<RankListData>()
//	private lateinit var rankAdapter: RankAdapter
//
//	// 차박지 포스트 리스트
//	private var postList = ArrayList<PostListData>()
//	private lateinit var postAdapter: PostAdapter
//
//	// 가까운 차박지 리스트
//	private var closeList = ArrayList<CloseListData>()
//	private lateinit var closeAdapter: CloseAdapter

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

//        // 차박지 랭킹 - 더미데이터
//        for (i in 1..5) {
//            val rankListData =
//                RankListData(
//                    i.toString(),
//                    R.drawable.rank_img_1,
//                    "구시포해수욕장${i}",
//                    "전북 고창군 상하면 자룡리${i}"
//                )
//            this.rankList.add(rankListData)
//        }
//
//        // 차박지 랭킹 - 어댑터 연결
//        rankAdapter = RankAdapter(requireContext(), rankList)
//        binding.recyclerRank.adapter = rankAdapter
//
//        // 차박지 포스트 - 더미데이터
//        for (i in 1..5) {
//            val postListData =
//                PostListData(
//                    R.drawable.post_img_1,
//                    "동화같은 풍경 속으로! 힐링하러 가보자~${i}",
//                    "BY tkdansla${i}"
//                )
//            this.postList.add(postListData)
//        }
//
//        // 가까운 차박지 - 어댑터 연결
//        postAdapter = PostAdapter(requireContext(), postList)
//        binding.recyclerPost.adapter = postAdapter
//
//        // 가까운 차박지 - 더미데이터
//        for (i in 1..5) {
//            val closeListData =
//                CloseListData(
//                    R.drawable.close_img_1,
//                    60 + i,
//                    "당진 왜목마을${i}",
//                    "충청남도 당진시 석문면 교로리 844-25${i}",
//                    R.drawable.bookmark
//                )
//            this.closeList.add(closeListData)
//        }
//
//        // 가까운 차박지 - 어댑터 연결
//        closeAdapter = CloseAdapter(requireContext(), closeList)
//        binding.recyclerCloser.adapter = closeAdapter

		getRank()
	}

	fun getRank(){
		val rankRetrofitInterface = ApplicationClass.sRetrofit.create(RankRetrofitInterface::class.java)
		rankRetrofitInterface.getGetRank().enqueue(object : Callback<Rank>{
			override fun onResponse(call: Call<Rank>, response: Response<Rank>) {
				var result = response.body() as Rank
				binding.recyclerRank.adapter = RankRecyclerView(result)
			}

			override fun onFailure(call: Call<Rank>, t: Throwable) {
				showCustomToast(t.message.toString())
			}
		})
	}

}