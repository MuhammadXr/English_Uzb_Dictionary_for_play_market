package uz.gita.english_uzbdictionary_for_play_market

import android.app.Application
import timber.log.Timber
import uz.gita.english_uzbdictionary_for_play_market.database.room.AppDataBase

class App:Application() {
    override fun onCreate() {
        super.onCreate()

        AppDataBase.init(this)


    }
}