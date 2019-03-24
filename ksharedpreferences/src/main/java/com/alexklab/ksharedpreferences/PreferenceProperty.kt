package com.alexklab.ksharedpreferences

import android.content.Context
import android.content.SharedPreferences
import kotlin.reflect.KProperty

/**
 * Base class for preference property
 *
 * @param preferenceKey the custom preference key, if this param is null then key will be generated from property name
 * @param defaultValue the preference default value
 * @param preferenceFileName the preferences file name.
 * @param operatingMode the preferences file operating mode
 */
abstract class PreferenceProperty<T : Any>(
    val preferenceKey: String? = null,
    val defaultValue: T,
    val preferenceFileName: String = DEFAULT_PREFERENCE_FILE_NAME,
    val operatingMode: Int = Context.MODE_PRIVATE
) {

    protected var preferencePropertyKey: String? = null

    protected val prefs: SharedPreferences by lazy {
        ApplicationContextHolder.applicationContext
            .getSharedPreferences(preferenceFileName, operatingMode)
    }

    /**
     * Returns the preference key.
     * If custom key is null than returns the property name in SCREAMING_SNAKE_CASE style
     *
     * @param property the metadata for the property.
     * @return the preference key
     */
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