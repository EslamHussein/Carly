package com.carly

import android.app.Application
import com.carly.core.di.coroutinesModule
import com.carly.core.di.dataStoreModule
import com.carly.core.di.jsonModule
import com.carly.core.di.localDBModule
import com.carly.features.addcar.di.addCarModule
import com.carly.features.dashboard.di.dashboardModule
import com.elvishew.xlog.LogConfiguration
import com.elvishew.xlog.LogItem
import com.elvishew.xlog.LogLevel
import com.elvishew.xlog.XLog
import com.elvishew.xlog.printer.AndroidPrinter
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CarlyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initLogging()
        startKoin {
            androidLogger()
            androidContext(this@CarlyApp)
            modules(
                coroutinesModule,
                jsonModule,
                localDBModule,
                dashboardModule,
                addCarModule,
                dataStoreModule
            )
        }
    }

    /**
     * Initializes the logging infrastructure.
     */
    private fun initLogging() {
        val appLogTag = getString(R.string.app_log_tag)
        val config = LogConfiguration.Builder().logLevel(
            LogLevel.ALL
        ).tag(appLogTag).addInterceptor { logItem ->
            // enhance the logging by appending the app log tag at the end of the initial log tag
            LogItem(
                logItem.level,
                "${logItem.tag}-$appLogTag",
                logItem.threadInfo,
                logItem.stackTraceInfo,
                logItem.msg
            )
        }.build()
        val printer = AndroidPrinter()
        XLog.init(config, printer)
    }
}