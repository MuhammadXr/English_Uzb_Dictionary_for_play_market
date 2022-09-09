package uz.gita.english_uzbdictionary_for_play_market.presenter

import android.database.Cursor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.english_uzbdictionary_for_play_market.database.model.WordData
import uz.gita.english_uzbdictionary_for_play_market.repository.Repository

class FavUzbViewModel : ViewModel(){
    private val repository = Repository

    val cursorOfWords = MutableLiveData<Cursor>()
    val showWordInfo = MutableLiveData<WordData>()
    val gotoFav = MutableLiveData<Unit>()

    init {
        viewModelScope.launch(Dispatchers.IO) {

            cursorOfWords.postValue(repository.getAllUzbFavorite())
        }
    }

    fun showData(wordData: WordData){
        showWordInfo.value = wordData
    }

    fun changeFavorite(wordData: WordData){
        viewModelScope.launch(Dispatchers.IO) {

            repository.update(wordData)
        }
    }

    fun searchBy(word:String){
        viewModelScope.launch(Dispatchers.IO) {

            cursorOfWords.postValue(repository.searchUzbBy(word))
        }
    }

    fun gotoFav(){
        gotoFav.value = Unit
    }
}