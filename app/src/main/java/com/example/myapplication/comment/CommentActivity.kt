package com.example.myapplication.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityCommentBinding

class CommentActivity : BaseActivity<ActivityCommentBinding>(ActivityCommentBinding::inflate) {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
	}
}