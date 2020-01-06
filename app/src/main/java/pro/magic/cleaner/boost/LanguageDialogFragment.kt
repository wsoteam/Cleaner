package pro.magic.cleaner.boost

import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AlertDialog

class LanguageDialogFragment : DialogFragment() {

    internal val itemsLang = arrayOf("English", "Russian", "Ukraine")
    internal val items = arrayOf("en", "ru", "uk")
    internal var lang: String? = null
    internal var activity = MainActivity()

}