package com.example.myapplication.map.detail.review.write.models

import android.util.Log
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.find.models.FindEmailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WriteCommentService(val view : WriteCommentView) {

	fun tryWriteComment(writeRequest : WriteCommentRequest, placeId : Int){
		val writeCommentRetrofitInterface = ApplicationClass.sRetrofit.create(WriteCommentRetrofitInterface::class.java)
		writeCommentRetrofitInterface.tryPostWriteComment(writeRequest, placeId).enqueue(object :
			Callback<WriteCommentResponse>{
			override fun onResponse(
				call: Call<WriteCommentResponse>,
				response: Response<WriteCommentResponse>
			) {
				val result = response.body() as WriteCommentResponse
				view.onPostWriteSuccess(response.body() as WriteCommentResponse)
			}

			override fun onFailure(call: Call<WriteCommentResponse>, t: Throwable) {
				view.onPostWriteFailure(t.message.toString())
			}
		})
	}

}