package cleaner.booster.wso.app

import android.app.Activity
import android.content.Context

import com.facebook.FacebookSdk

import com.facebook.appevents.AppEventsLogger
import com.google.android.gms.ads.MobileAds
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig

import com.facebook.FacebookSdk.setAutoLogAppEventsEnabled
import com.google.firebase.analytics.FirebaseAnalytics
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
        val mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        MobileAds.initialize(this, "ca-app-pub-3050564412171997~4260778012") //ca-app-pub-9387354664905418~6073119457
        // Создание расширенной конфигурации библиотеки.
        val config = YandexMetricaConfig.newConfigBuilder("2e345e4f-9c28-471c-90b2-fffc70a053c8").build()
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