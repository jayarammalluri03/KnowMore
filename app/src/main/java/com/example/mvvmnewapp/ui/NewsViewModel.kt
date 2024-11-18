package com.example.mvvmnewapp.MvvmApp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmnewapp.MvvmApp.models.NewsResponse
import com.example.mvvmnewapp.MvvmApp.Resource
import kotlinx.coroutines.launch

class NewsViewModel(
    val newsRepository: NewsRespository
) : ViewModel() {

    init {
        getBreakingNews("Us")
    }

    var breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData() // Fixed initialization
    var breakingNewspage = 1

    var searchedNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData() // Fixed initialization
    var searchNewspage = 1

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews = MutableLiveData()
        breakingNewspage = 1
        breakingNews.postValue(Resource.Loading())
        val response= newsRepository.getBreakingNews(countryCode,breakingNewspage)
        Log.i("breakingnews", (response?.body()?.articles?.size ?: 0).toString())
        breakingNews.postValue(handlebreakingNewsResponse(response))
    }

    fun getSearchedNews(SearchQuery: String) = viewModelScope.launch {
        //searchedNews=  MutableLiveData()
       // searchNewspage= 1
        searchedNews.postValue(Resource.Loading())
        val response= newsRepository.getSearchedNews(SearchQuery,searchNewspage)
        Log.i("breakingnews", (response?.body()?.articles?.size ?: 0).toString())
        searchedNews.postValue(handlesearchedNews(response))
    }


    fun handlebreakingNewsResponse(response: retrofit2.Response<NewsResponse>) : Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun handlesearchedNews(response: retrofit2.Response<NewsResponse>) : Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}