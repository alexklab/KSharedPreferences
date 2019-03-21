package com.alexklab.ksharedpreferences

import android.content.Context
import android.content.SharedPreferences
import kotlin.reflect.KProperty

abstract class PreferenceProperty<T : Any>(
    val preferenceKey: String? = null,
    val defaultValue: T,
    val preferenceFileName: String = DEFAULT_PREFERENCE_FILE_NAME,
    val operatingMode: Int = Context.MODE_PRIVATE
) {

    protected var preferencePropertyKey: String? = null

    protected val prefs: SharedPreferences by lazy {
        PreferencesContextHolder.context
            .getSharedPreferences(preferenceFileName, operatingMode)
    }

    protected fun getPreferenceKey(property: KProperty<*>): String {
        return preferenceKey
            ?: preferencePropertyKey
            ?: property.name
                .toScreamingSnakeCase()
                .apply { preferencePropertyKey = this }
    }

    companion object {
        const val DEFAULT_PREFERENCE_FILE_NAME = "prefs"
    }
}