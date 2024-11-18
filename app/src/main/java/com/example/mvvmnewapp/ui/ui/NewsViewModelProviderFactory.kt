package com.example.mvvmnewapp.MvvmApp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmnewapp.MvvmApp.NewsRespository
import com.example.mvvmnewapp.MvvmApp.NewsViewModel
class NewsViewModelProviderFactory(
    private val newsRepository: NewsRespository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(newsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
