package uz.gita.english_uzbdictionary_for_play_market.utils

import android.database.Cursor
import uz.gita.english_uzbdictionary_for_play_market.database.model.WordData

fun Cursor.getWordDataList(): List<WordData> {
    val list = ArrayList<WordData>()
    while (moveToNext()) {
        list.add(
            WordData(
                getLong(0),
                getString(1),
                getString(2),
                getString(3),
                getString(4),
                getString(5),
                getInt(6),
                getInt(7)
            )
        )
    }
    return list
}