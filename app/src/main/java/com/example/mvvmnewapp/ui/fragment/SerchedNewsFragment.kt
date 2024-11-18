package com.example.mvvmnewapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.mvvmnewapp.MvvmApp.NewsViewModel
import com.example.mvvmnewapp.MvvmApp.Resource
import com.example.mvvmnewapp.NewsActivity
import com.example.mvvmnewapp.R
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmnewapp.MvvmApp.Adapter.NewsAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SerchedNewsFragment : Fragment(R.layout.fragment_search_news) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    lateinit var rvBreakingNews: RecyclerView
    lateinit var errorLayout: RelativeLayout
    lateinit var errorMessage: AppCompatTextView
    lateinit var list_Layout: RelativeLayout
    lateinit var edit_query: AppCompatEditText
    lateinit var clear_text: AppCompatImageView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        errorLayout= view.findViewById(R.id.error_layout)
        list_Layout= view.findViewById(R.id.list_Layout)
        rvBreakingNews=view.findViewById(R.id.rvBreakingNews)
        errorMessage=view.findViewById(R.id.errorMessage)
        edit_query=view.findViewById(R.id.edit_query)
        clear_text= view.findViewById(R.id.clear_text)
        clear_text.setOnClickListener(View.OnClickListener {
            edit_query.setText("")
        })
        setUpRecyclerView()
        var job: Job? = null
        edit_query.addTextChangedListener {
            job?.cancel()
            job= MainScope().launch {
                delay(500L)
                Log.i("searchedtext",edit_query.text.toString())
                if(edit_query.toString().isNotEmpty()){
                    viewModel.getSearchedNews(edit_query.text.toString())
                }
            }
        }
        viewModel.searchedNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }

                is Resource.Error -> {
                    Log.i("breakingnews", "error")

                }
                is Resource.Loading -> {
                    Log.i("breakingnews", "loading")
                }
                else -> {
                    Log.i("breakingnews", "apifailed")
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