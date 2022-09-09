package uz.gita.english_uzbdictionary_for_play_market.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuViewModel:ViewModel() {
    val gotoEngDic = MutableLiveData<Boolean>()

    fun gotoEngDic(boolean: Boolean){
        gotoEngDic.value = boolean
    }
}