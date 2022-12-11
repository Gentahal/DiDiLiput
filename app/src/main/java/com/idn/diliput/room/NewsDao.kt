package com.idn.diliput.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.idn.diliput.entity.NewsEntity


@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news : NewsEntity)

    @Delete
    fun delete(news : NewsEntity)

    @Query("SELECT * FROM newstable")
    fun getAllData() : LiveData<List<NewsEntity>>

    @Query("SELECT COUNT(1) FROM newsTable WHERE title = :title")
    fun isBookmark(title: String): LiveData<Int>
}