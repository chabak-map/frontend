package com.example.myapplication.post.detail.comment.write.models

import com.example.myapplication.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WritePostCommentService(val view : WritePostCommentView) {

	fun tryWriteComment(writePostComment: WritePostCommentRequest, postId: Int){
		val writePostCommentRetrofitInterface = ApplicationClass.sRetrofit.create(WritePostCommentRetrofitInterface::class.java)
		writePostCommentRetrofitInterface.tryPostComments(writePostComment, postId).enqueue(object : Callback<WritePostCommentResponse>{
			override fun onResponse(
				call: Call<WritePostCommentResponse>,
				response: Response<WritePostCommentResponse>
			) {
				view.onPostWriteSuccess(response.body() as WritePostCommentResponse)
			}

			override fun onFailure(call: Call<WritePostCommentResponse>, t: Throwable) {
				view.onPostWriteFailure(t.message.toString())
			}
		})
	}
}