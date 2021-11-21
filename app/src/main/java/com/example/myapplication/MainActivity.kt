package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.map.MapFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home -> {
                startActivity(Intent(this, MainActivity::class.java))
                return true
            }

            R.id.map -> {
                supportFragmentManager.beginTransaction().replace(R.id.pager, MapFragment())
                    .commit()
                return true
            }
        }
        return false
    }
}