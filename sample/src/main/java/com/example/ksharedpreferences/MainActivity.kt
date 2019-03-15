package com.example.ksharedpreferences

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alexklab.ksharedpreferences.KSharedPreference
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("onCreate", "lastUsageTime: ${Date(Prefs.lastUsageTime)}")
        Log.d("onCreate", "userID: ${Prefs.userID}")
        Prefs.lastUsageTime = Date().time
    }
}

object Prefs {

    var userID by KSharedPreference(defaultValue = "NONE")
    var lastUsageTime: Long by KSharedPreference(defaultValue = 0L)
}