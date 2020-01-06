package pro.magic.cleaner.boost.utils

import android.content.Context
import pro.magic.cleaner.boost.MyApp

object PreferencesProvider{

    private val preferences = MyApp.getInstance().getSharedPreferences("waseem", Context.MODE_PRIVATE)

    fun getInstance() = preferences
}