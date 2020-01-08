package cleaner.boost.wso.app

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.util.Log

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.hookedonplay.decoviewlib.charts.SeriesItem
import com.hookedonplay.decoviewlib.events.DecoEvent

import cleaner.boost.wso.app.PPP.PowerSaving_Complition

import cleaner.boost.wso.app.Constants.adsBatterySaver
import cleaner.boost.wso.app.Constants.adsShow
import cleaner.boost.wso.app.utils.PreferencesProvider
import kotlinx.android.synthetic.main.revert_to_normal.*

class Noraml_Mode : Activity(), AdMobFullscreenManager.AdMobFullscreenDelegate {

    internal var check = 0
    internal var fullscreenManager: AdMobFullscreenManager? = null
    private var mAdView: AdView? = null

    private val adManager: AdMobFullscreenManager?
        get() {
            if (fullscreenManager == null) {
                configureManager()
            }
            return fullscreenManager
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.revert_to_normal)

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView!!.loadAd(adRequest)
        if (adsBatterySaver && adsShow) {
        }
        adsBatterySaver = !adsBatterySaver

        dynamicArcView2.addSeries(SeriesItem.Builder(Color.parseColor("#27282D"))
                .setRange(0f, 100f, 100f)
                .setInitialVisibility(false)
                .setLineWidth(12f)
                .build())

        val seriesItem2 = SeriesItem.Builder(Color.parseColor("#FFFFFF"))
                .setRange(0f, 100f, 0f)
                .setLineWidth(10f)
                .build()

        val series1Index2 = dynamicArcView2.addSeries(seriesItem2)



        dynamicArcView2.addEvent(DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
                .setDelay(0)
                .setDuration(0)
                .setListener(object : DecoEvent.ExecuteEventListener {
                    override fun onEventStart(decoEvent: DecoEvent) {

                    }

                    override fun onEventEnd(decoEvent: DecoEvent) {

                    }

                })
                .build())

        dynamicArcView2.addEvent(DecoEvent.Builder(100f).setIndex(series1Index2).setDelay(1000).setListener(object : DecoEvent.ExecuteEventListener {
            override fun onEventStart(decoEvent: DecoEvent) {

            }

            override fun onEventEnd(decoEvent: DecoEvent) {
                if (adsShow) {
                    adManager!!.completed()
                } else {
                    check = 1
                    youDesirePermissionCode(this@Noraml_Mode)
                    PreferencesProvider.getInstance().edit()
                            .putString("mode", "0")
                            .apply()
                }
            }
        }).build())
    }

    fun enablesall() {
        PowerSaving_Complition.setAutoOrientationEnabled(applicationContext, true)
        Settings.System.putInt(this.contentResolver, Settings.System.SCREEN_BRIGHTNESS, 255)
        ContentResolver.setMasterSyncAutomatically(true)
    }

    fun youDesirePermissionCode(context: Activity) {
        val permission: Boolean
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permission = Settings.System.canWrite(context)
        } else {
            permission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_SETTINGS) == PackageManager.PERMISSION_GRANTED
        }
        if (permission) {
            enablesall()
            finish()
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                intent.data = Uri.parse("package:" + context.packageName)
                context.startActivityForResult(intent, 1)
            } else {
                ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.WRITE_SETTINGS), 1)
            }
        }
    }

    //
    @SuppressLint("NewApi")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && Settings.System.canWrite(this)) {
            enablesall()
            finish()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            enablesall()
            finish()
        }
    }


    override fun onResume() {
        super.onResume()
        if (check == 1) {
            try {
                enablesall()
            } catch (e: Exception) {
                finish()
            }
            finish()
        }
    }

    private fun configureManager() {
        if (fullscreenManager == null) {
            fullscreenManager = AdMobFullscreenManager(this, this)
        } else {
            fullscreenManager!!.reloadAd()
        }
    }

    override fun ADLoaded() {
        if (adManager!!.tryingShowDone) {
            adManager!!.showAdd()
        }
    }

    override fun ADIsClosed() {
        if (adManager!!.tryingShowDone) {
            check = 1
            youDesirePermissionCode(this@Noraml_Mode)
            PreferencesProvider.getInstance().edit()
                    .putString("mode", "0")
                    .apply()
        }
    }


}
