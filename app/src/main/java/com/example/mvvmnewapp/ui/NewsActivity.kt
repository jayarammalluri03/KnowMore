package com.example.mvvmnewapp
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmnewapp.MvvmApp.NewsViewModel
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.mvvmnewapp.MvvmApp.NewsRespository
import com.example.mvvmnewapp.MvvmApp.db.ArticleDataBase
import com.example.mvvmnewapp.MvvmApp.ui.NewsViewModelProviderFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class NewsActivity : AppCompatActivity() {
    lateinit var viewModel: NewsViewModel
    lateinit var myDB: ArticleDataBase
    lateinit var actionbar_image: AppCompatImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        val newsRepository = NewsRespository(ArticleDataBase.invoke(applicationContext))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
        actionbar_image=findViewById(R.id.actionbar_ImageView)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }
}
