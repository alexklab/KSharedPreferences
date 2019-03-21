package com.alexklab.ksharedpreferences

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SharedPreferenceProperty<T : Any>(
    key: String? = null,
    defaultValue: T,
    fileName: String = DEFAULT_PREFERENCE_FILE_NAME,
    operatingMode: Int = Context.MODE_PRIVATE
) : PreferenceProperty<T>(key, defaultValue, fileName, operatingMode), ReadWriteProperty<Any, T> {

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return prefs.getValue(getPreferenceKey(property), defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        prefs.edit { putValue(getPreferenceKey(property), value) }
    }
}