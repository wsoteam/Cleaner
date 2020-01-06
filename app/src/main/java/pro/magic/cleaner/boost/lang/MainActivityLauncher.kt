package pro.magic.cleaner.boost.lang


import java.util.Locale

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.preference.PreferenceManager

import pro.magic.cleaner.boost.MainActivity
import pro.magic.cleaner.boost.R

class MainActivityLauncher : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.flash_screen)
        updateLocaleIfNeeded()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun updateLocaleIfNeeded() {
        val sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this)

        if (sharedPreferences.contains(SettingsFragment.LANGUAGE_SETTING)) {
            val locale = sharedPreferences.getString(
                    SettingsFragment.LANGUAGE_SETTING, "")
            val localeSetting = Locale(locale)

            if (localeSetting != Locale.getDefault()) {
                val resources = resources
                val conf = resources.configuration
                conf.locale = localeSetting
                resources.updateConfiguration(conf,
                        resources.displayMetrics)

                val refresh = Intent(this, MainActivityLauncher::class.java)
                startActivity(refresh)
                finish()
            }
        }
    }
}