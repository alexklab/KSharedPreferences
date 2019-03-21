package com.alexklab.ksharedpreferences

import android.content.Context
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class LivePreferenceProperty<T : Any>(
    key: String? = null,
    defaultValue: T,
    fileName: String = DEFAULT_PREFERENCE_FILE_NAME,
    mode: Int = Context.MODE_PRIVATE
) : PreferenceProperty<T>(key, defaultValue, fileName, mode), ReadOnlyProperty<Any, LivePreference<T>> {

    override fun getValue(thisRef: Any, property: KProperty<*>): LivePreference<T> = LivePreference(
        key = getPreferenceKey(property),
        defaultValue = defaultValue,
        prefs = prefs
    )
}