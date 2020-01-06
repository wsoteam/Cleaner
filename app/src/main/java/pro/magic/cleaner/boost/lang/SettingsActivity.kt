package pro.magic.cleaner.boost.lang


import android.app.FragmentManager
import android.os.Bundle
import androidx.fragment.app.FragmentActivity

import pro.magic.cleaner.boost.R

class SettingsActivity : FragmentActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        val fragmentManager = fragmentManager
        if (savedInstanceState == null) {
            val settingsFragment = SettingsFragment()
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, settingsFragment)
                    .commit()
        }
    }
}