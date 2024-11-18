package com.example.mvvmnewapp.MvvmApp.db
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvmnewapp.MvvmApp.db.ArticleDao
import com.example.mvvmnewapp.MvvmApp.db.Converter
import com.example.mvvmnewapp.MvvmApp.models.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ArticleDataBase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile
        private var instance: ArticleDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): ArticleDataBase =
            instance ?: synchronized(LOCK) {
                instance ?: createDatabase(context).also { instance = it }
            }

        private fun createDatabase(context: Context): ArticleDataBase =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDataBase::class.java,
                "article_db.db"
            ).fallbackToDestructiveMigration() // Optional: add this for easier migrations
                .build()
    }
}
