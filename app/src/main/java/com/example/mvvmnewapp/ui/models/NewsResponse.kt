package com.example.mvvmnewapp.MvvmApp.models

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)