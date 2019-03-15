package com.alexklab.ksharedpreferences

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class KSharedPreference<T : Any>(
    val key: String? = null,
    val defaultValue: T,
    val fileName: String = "prefs",
    val mode: Int = Context.MODE_PRIVATE
) : ReadWriteProperty<Any, T> {

    private var defaultPreferenceKey: String? = null

    private val pref: SharedPreferences by lazy {
        PreferencesContextHolder.context.getSharedPreferences(fileName, mode)
    }

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        val key = property.toPreferenceKey()

        return with(pref) {
            val result: Any = when (defaultValue) {
                is Boolean -> getBoolean(key, defaultValue)
                is Int -> getInt(key, defaultValue)
                is Long -> getLong(key, defaultValue)
                is Float -> getFloat(key, defaultValue)
                is String -> getString(key, defaultValue)
                is Set<*> -> {
                    (defaultValue as? Set<String>)
                        ?.apply { getStringSet(key, this) }
                        ?: throw IllegalArgumentException("Unsupported value type: ${defaultValue.javaClass}, only StringSet allowed ")
                }
                else -> throw IllegalArgumentException("Unsupported value type: ${defaultValue.javaClass}")
            }
            result as T
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        val key = property.toPreferenceKey()

        pref.edit {
            when (value) {
                is Boolean -> putBoolean(key, value)
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is Float -> putFloat(key, value)
                is String -> putString(key, value)
                is Set<*> -> {
                    (value as? Set<String>)
                        ?.apply { putStringSet(key, this) }
                        ?: throw IllegalArgumentException("Unsupported value type: ${defaultValue.javaClass}, only StringSet allowed ")
                }
                else -> throw IllegalArgumentException("Unsupported value type: ${defaultValue.javaClass}")
            }
        }
    }

    fun defaultKey(): String? = defaultPreferenceKey

    private fun KProperty<*>.toPreferenceKey(): String {
        return key
            ?: defaultPreferenceKey
            ?: this@toPreferenceKey.name
                .toScreamingSnakeCase()
                .apply { defaultPreferenceKey = this }
    }
}