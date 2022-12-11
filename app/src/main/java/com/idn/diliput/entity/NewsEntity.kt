package com.idn.diliput.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.idn.diliput.response.ArticlesItem
import com.idn.diliput.response.Source

@Entity(tableName = "newsTable")
data class NewsEntity(
    @PrimaryKey
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "author")
    val author: String?,

    @ColumnInfo(name = "urlToImage")
    val urlToImage: String?,

    @ColumnInfo(name = "content")
    val content: String?,

    @ColumnInfo(name = "name")
    val name: String?,
) {
    fun asBookmarkResponse() = ArticlesItem(
        title = title,
        author = author,
        urlToImage = urlToImage,
        content = content,
        source = Source(
            name = name
        )
    )
}

