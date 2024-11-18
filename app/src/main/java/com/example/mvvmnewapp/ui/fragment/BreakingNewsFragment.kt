package com.example.mvvmnewapp.MvvmApp.fragment

import android.app.Activity
import android.media.tv.TvContract.Programs
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmnewapp.MvvmApp.Adapter.NewsAdapter
import com.example.mvvmnewapp.MvvmApp.NewsViewModel
import com.example.mvvmnewapp.MvvmApp.Resource
import com.example.mvvmnewapp.NewsActivity
import com.example.mvvmnewapp.R

class BreakingNewsFragment: Fragment(R.layout.fragment_breaking_news) {

    lateinit var progressBar: ProgressBar
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    lateinit var rvBreakingNews: RecyclerView
    lateinit var errorLayout: RelativeLayout
    lateinit var errorMessage: AppCompatTextView
    lateinit var list_Layout: RelativeLayout
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as NewsActivity).viewModel
        errorLayout= view.findViewById(R.id.error_layout)
        list_Layout= view.findViewById(R.id.list_Layout)
        rvBreakingNews=view.findViewById(R.id.rvBreakingNews)
        errorMessage=view.findViewById(R.id.errorMessage)
        progressBar=view.findViewById(R.id.paginationprogressbar)
        setUpRecyclerView()
        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {
            response ->
            when(response){
                is Resource.Success ->{
                    response.data?.let {
                        newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }is Resource.Error  ->{
                    errorLayout.visibility=View.VISIBLE
                    list_Layout.visibility=View.GONE
                    errorMessage.text=response.message
            }is Resource.Loading->{
                errorLayout.visibility=View.GONE
                list_Layout.visibility=View.VISIBLE
                progressBar.visibility=View.VISIBLE
            }

                else -> {
                    Log.i("breakingnews","apifailed")
                }
            }
        })
    }

    private fun setUpRecyclerView(){
        newsAdapter= NewsAdapter()
        rvBreakingNews.apply {
            adapter=newsAdapter
            layoutManager= LinearLayoutManager(activity)
        }
    }
}