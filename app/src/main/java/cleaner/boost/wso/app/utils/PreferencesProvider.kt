package cleaner.boost.wso.app.utils

import android.content.Context
import cleaner.boost.wso.app.MyApp

object PreferencesProvider{

    private val preferences = MyApp.getInstance().getSharedPreferences("waseem", Context.MODE_PRIVATE)

    fun getInstance() = preferences
}