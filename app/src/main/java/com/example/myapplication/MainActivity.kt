package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.map.MapFragment
import com.example.myapplication.post.place.PlaceFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
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
                supportFragmentManager.beginTransaction().replace(R.id.main_frame, PlaceFragment())
                    .commit()
                return true
            }
        }
        return false
    }
}