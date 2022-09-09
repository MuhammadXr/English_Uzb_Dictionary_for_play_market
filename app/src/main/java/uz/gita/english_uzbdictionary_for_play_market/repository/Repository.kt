package uz.gita.english_uzbdictionary_for_play_market.repository

import android.database.Cursor
import androidx.lifecycle.LiveData
import uz.gita.english_uzbdictionary_for_play_market.database.model.WordData
import uz.gita.english_uzbdictionary_for_play_market.database.room.AppDataBase

object Repository {
    private val wordDao = AppDataBase.getDB().getDao()

    fun update(wordData: WordData){
        wordDao.update(wordData)
    }

    fun getAllEngWords(): Cursor = wordDao.getAllEngWord()

    fun getAllUzbWords(): Cursor = wordDao.getAllUzbWord()

    fun getAllEngFavorite(): Cursor = wordDao.getAllEngFavorite()

    fun getAllUzbFavorite(): Cursor = wordDao.getAllUzbFavorite()

    fun searchUzbBy(word:String): Cursor = wordDao.searchUzbekWordsBy(word)

    fun searchEngBy(word:String): Cursor = wordDao.searchEnglishWordsBy(word)
}