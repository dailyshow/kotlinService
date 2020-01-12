package com.cis.kotlinservice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat

class ServiceClass3 : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // 오레오 버전 이상일 경우에는 5초안에 notification message 를 띄워줘야 강제 종료되지 않고 계속 실행됨.
        var builder : NotificationCompat.Builder? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            var channel = NotificationChannel("test1", "serviceNoti", NotificationManager.IMPORTANCE_HIGH)
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            manager.createNotificationChannel(channel)

            builder = NotificationCompat.Builder(this, "test1")
        } else {
            builder = NotificationCompat.Builder(this)
        }

        builder?.setSmallIcon(android.R.drawable.ic_dialog_info)
        builder?.setContentTitle("서비스 가동")
        builder?.setContentText("서비스를 가동중입니다.")
        var notification = builder?.build()

        startForeground(10, notification)


        val thread = ThreadClass()
        thread.start()

        return super.onStartCommand(intent, flags, startId)
    }

    inner class ThreadClass : Thread() {
        override fun run() {
            var idx = 0
            while (idx < 10) {
                SystemClock.sleep(1000)
                Log.d("time3", "foreground service run : ${idx}")
                idx++
            }
            Log.d("time3", "foreground service 종료됨")
        }
    }
}
