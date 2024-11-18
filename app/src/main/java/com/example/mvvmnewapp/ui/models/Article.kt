package com.example.mvvmnewapp.MvvmApp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mvvmnewapp.MvvmApp.db.Converter

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    @TypeConverters(Converter::class) val source: Source,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)
