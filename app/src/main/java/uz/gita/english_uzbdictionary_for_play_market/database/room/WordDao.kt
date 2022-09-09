package uz.gita.english_uzbdictionary_for_play_market.database.room

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import uz.gita.english_uzbdictionary_for_play_market.database.model.WordData

@Dao
interface WordDao {

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(wordData: WordData)

    @Query("SELECT * FROM dictionary")
    fun getAllEngWord(): Cursor

    @Query("SELECT * FROM dictionary order by uzbek")
    fun getAllUzbWord(): Cursor

    @Query("SELECT * FROM dictionary Where is_favourite = 1")
    fun getAllEngFavorite(): Cursor

    @Query("SELECT * FROM dictionary Where is_favorite_uzb = 1")
    fun getAllUzbFavorite(): Cursor

    @Query("SELECT * FROM dictionary WHERE english LiKE '%' || :word || '%'")
    fun searchEnglishWordsBy(word:String) : Cursor

    @Query("SELECT * FROM dictionary WHERE uzbek LiKE '%' || :word || '%'")
    fun searchUzbekWordsBy(word:String) : Cursor

}