package com.example.myapplication

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.home.HomeFragment
import com.example.myapplication.map.MapFragment
import com.example.myapplication.post.PostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this)
        // 켜졌을 때 선택되는 첫화면
        binding.bottomNavigation.selectedItemId = R.id.home
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                supportFragmentManager.beginTransaction().replace(R.id.main_frame, HomeFragment())
                    .commit()
                return true
            }

            R.id.map -> {
                supportFragmentManager.beginTransaction().replace(R.id.main_frame, MapFragment())
                    .commit()
                return true
            }

            R.id.review -> {
                supportFragmentManager.beginTransaction().replace(R.id.main_frame, PostFragment())
                    .commit()
                return true
            }
        }
        return false
    }

}