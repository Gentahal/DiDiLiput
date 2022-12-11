package com.idn.diliput.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.idn.diliput.entity.NewsEntity


@Database(entities = [NewsEntity::class], version = 2)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao() : NewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context) : NewsDatabase {
            if (INSTANCE == null) {
                synchronized(NewsDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        NewsDatabase::class.java,
                        "bookmark_database"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE as NewsDatabase
        }

    }
}