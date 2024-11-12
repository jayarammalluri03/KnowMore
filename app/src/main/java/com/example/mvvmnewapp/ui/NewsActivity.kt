package com.example.mvvmnewapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mvvmnewapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        val bottomNavigationView: BottomNavigationView =findViewById(R.id.bottomNavigationView)
        val newsNavHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment)
        bottomNavigationView.setupWithNavController(newsNavHostFragment!!.findNavController())
    }
}