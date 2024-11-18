package com.example.mvvmnewapp.MvvmApp
import com.example.mvvmnewapp.MvvmApp.api.RetrofitInstance
import com.example.mvvmnewapp.MvvmApp.db.ArticleDataBase

class NewsRespository (val db : ArticleDataBase) {
        suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
            RetrofitInstance.api.getBreakingnews(countryCode,pageNumber)

        suspend fun getSearchedNews(searchQuery: String,pageNumber: Int)=
            RetrofitInstance.api.searchForNews(searchQuery,pageNumber)

}
