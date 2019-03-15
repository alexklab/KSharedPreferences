package com.example.ksharedpreferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexklab.ksharedpreferences.KSharedPreference

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val property = KSharedPreference()
    }
}
