package com.example.mvvmnewapp.MvvmApp.db

import com.example.mvvmnewapp.MvvmApp.models.Source
import androidx.room.TypeConverter

class Converter {

    @TypeConverter
    fun fromSource(source: Source): String? {
        return  source.name
    }

    @TypeConverter
    fun toSource(name: String): Source? {
        return if (name != null) Source(id = null, name = name) else null
    }

}