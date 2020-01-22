package com.alexklab.ksharedpreferences

import android.content.SharedPreferences

interface PreferencesHolder {
    val prefs: Lazy<SharedPreferences>
}