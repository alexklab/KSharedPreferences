package com.example.ksharedpreferences

import com.alexklab.ksharedpreferences.LivePreference
import com.alexklab.ksharedpreferences.LivePreferenceProperty
import com.alexklab.ksharedpreferences.SharedPreferenceProperty
import com.alexklab.ksharedpreferences.SharedPreferencesHolder
import java.util.*

object Prefs : SharedPreferencesHolder() {

    var uid: Long by SharedPreferenceProperty(defaultValue = 0)

    var text: String by SharedPreferenceProperty(defaultValue = "")

    val trigger: LivePreference<Boolean> by LivePreferenceProperty(defaultValue = false)

    var lastUsageTime: Long by SharedPreferenceProperty(defaultValue = Date().time)
}