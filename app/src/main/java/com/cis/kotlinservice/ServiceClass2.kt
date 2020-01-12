package com.cis.kotlinservice

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.os.SystemClock
import android.util.Log

class ServiceClass2 : IntentService("ServiceClass2") {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onHandleIntent(intent: Intent?) {
        var idx = 0
        while (idx < 5) {
            SystemClock.sleep(1000)
            Log.d("time2", "intent service running : ${idx}")
            idx++
        }
        Log.d("time2", "intent service 종료됨")
    }
}
