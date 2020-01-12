package com.cis.kotlinservice

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var service_intent : Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startBtn.setOnClickListener { view ->
            service_intent = Intent(this, ServiceClass1::class.java)
            startService(service_intent)
        }

        stopBtn.setOnClickListener { view ->
            stopService(service_intent)
        }

        intentServiceBtn.setOnClickListener { view ->
            service_intent = Intent(this, ServiceClass2::class.java)
            startService(service_intent)
        }

        // foreground service 는 메모리가 부족한 현상이 나타나도 시스템이 강제종료 시키지 않는 서비스이다.
        foregroundServiceBtn.setOnClickListener { view ->
            service_intent = Intent(this, ServiceClass3::class.java)
            // foreground service 는 오레오버전 이상일 경우에 메소드를 다른걸 써줘야 한다.
            // 오레오 버전 이상일 경우에는 5초안에 notification message 를 띄워줘야 강제 종료되지 않고 계속 실행됨.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(service_intent)
            } else {
                startService(service_intent)
            }
        }
    }
}
