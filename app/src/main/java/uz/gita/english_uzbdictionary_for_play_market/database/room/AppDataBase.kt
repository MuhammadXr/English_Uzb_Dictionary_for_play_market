package uz.gita.english_uzbdictionary_for_play_market.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.english_uzbdictionary_for_play_market.database.model.WordData

@Database(
    entities = [WordData::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        lateinit var dataBase: AppDataBase

        fun init(context: Context) {
            if (!Companion::dataBase.isInitialized)
            dataBase = Room.databaseBuilder(context, AppDataBase::class.java, "app_db")
                .createFromAsset("dictionary.db")
                .build()
        }

        fun getDB(): AppDataBase
        {
            return dataBase
        }
    }

    abstract fun getDao(): WordDao
}