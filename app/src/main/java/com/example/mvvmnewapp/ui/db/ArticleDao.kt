package com.example.mvvmnewapp.MvvmApp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmnewapp.MvvmApp.models.Article

@Dao
abstract  class ArticleDao {

    // Corrected return type to Long for upsert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun upsert(article: Article): Long

    // Query to get all articles
    @Query("SELECT * FROM articles")
    abstract fun getAllArticles(): LiveData<List<Article>>

    // Corrected return type to Int for delete
    @Delete
    abstract fun deleteArticle(article: Article): Int
}
