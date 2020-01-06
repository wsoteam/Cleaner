package pro.magic.cleaner.boost

import android.app.Activity
import android.app.Application
import android.content.Context
import android.widget.Toast

import com.facebook.FacebookSdk

import com.facebook.appevents.AppEventsLogger
import com.google.android.gms.ads.MobileAds
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig

import com.facebook.FacebookSdk.setAutoLogAppEventsEnabled
import com.rt.ModuleApplication
import ru.mail.aslanisl.mobpirate.MobPirate

class MyApp : ModuleApplication() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"))
    }


    override fun onCreate() {
        super.onCreate()
        sInstance = this
        SubscriptionProvider.init(this)
        FacebookSdk.sdkInitialize(applicationContext)
        setAutoLogAppEventsEnabled(true)
        AppEventsLogger.activateApp(this)
        MobPirate.getInstance().init(this, getString(R.string.facebook_app_id))

        MobileAds.initialize(this, "ca-app-pub-3050564412171997~4260778012") //ca-app-pub-9387354664905418~6073119457
        // Создание расширенной конфигурации библиотеки.
        val config = YandexMetricaConfig.newConfigBuilder("89aa19ff-01d3-4d23-94f2-d95ce756ae1e").build()
        // Инициализация AppMetrica SDK.
        YandexMetrica.activate(applicationContext, config)
        // Отслеживание активности пользователей.
        YandexMetrica.enableActivityAutoTracking(this)
    }

    companion object {

        private lateinit var sInstance: MyApp

        fun getInstance(): MyApp {
            return sInstance
        }

        /**
         * Returns an instance of [MyApp] attached to the passed activity.
         */
        fun getInstance(activity: Activity): MyApp {
            return activity.application as MyApp
        }
    }

}