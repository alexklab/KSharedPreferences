package com.example.ksharedpreferences

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment1, EditPrefsFragment())
            .replace(R.id.fragment2, ShowPrefsFragment())
            .commit()

        Log.d("LastUsage", "Date: ${Date(Prefs.lastUsageTime)}")
        Prefs.lastUsageTime = Date().time
    }
}