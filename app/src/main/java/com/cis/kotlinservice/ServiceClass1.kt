package com.cis.kotlinservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock
import android.util.Log

class ServiceClass1 : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    // service 가 실행되면 자동으로 실행되는 메소
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val thread = ThreadClass()
        thread.start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("time", "서비스 실행 종료")
    }

    inner class ThreadClass : Thread() {
        override fun run() {
            var idx = 0
            while (idx < 5){
                SystemClock.sleep(1000)
//                var time = System.currentTimeMillis()
                Log.d("time", "service running : ${idx}")
                idx++
            }
            Log.d("time", "service 종료됨")
        }
    }
}
