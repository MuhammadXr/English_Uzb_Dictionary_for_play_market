package uz.gita.english_uzbdictionary_for_play_market.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {
    val gotoMenu = MutableLiveData<Unit>()

    fun gotoMenu(){
        viewModelScope.launch {
            delay(1500)
            gotoMenu.value = Unit
        }
    }
}