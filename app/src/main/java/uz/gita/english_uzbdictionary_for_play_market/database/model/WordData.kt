package uz.gita.english_uzbdictionary_for_play_market.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "dictionary")
data class WordData(
    @PrimaryKey
    val id: Long,
    val english: String,
    val type: String,
    val transcript: String,
    val uzbek: String,
    val countable: String,
    @ColumnInfo(name = "is_favourite")
    var isFavourite: Int,
    @ColumnInfo(name = "is_favorite_uzb")
    var isUzbFavourite: Int

): Serializable