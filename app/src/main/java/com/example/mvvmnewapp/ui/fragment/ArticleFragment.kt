package com.example.mvvmnewapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mvvmnewapp.MvvmApp.NewsViewModel
import com.example.mvvmnewapp.R
import com.example.mvvmnewapp.NewsActivity

class ArticleFragment: Fragment(R.layout.fragment_article) {

        private lateinit var viewModel: NewsViewModel
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
           // viewModel = (activity as? NewsActivity)?.viewModel as NewsViewModel

        }

}