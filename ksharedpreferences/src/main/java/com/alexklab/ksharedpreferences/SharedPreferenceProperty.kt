package com.alexklab.ksharedpreferences

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


/**
 * Class that can be used for implementing property delegates of SharedPreference values.
 * Allowed types: Boolean, Int, Long, Float, String, Set<String>
 *
 * @param T the type of the preference value.
 * @param key the custom preference key, if this param is null then key will be generated from property name
 * @param defaultValue the preference default value
 * @param fileName the preferences file name.
 * @param operatingMode the preferences file operating mode
 */
open class SharedPreferenceProperty<T : Any>(
    key: String? = null,
    defaultValue: T,
    fileName: String = DEFAULT_PREFERENCE_FILE_NAME,
    operatingMode: Int = Context.MODE_PRIVATE
) : PreferenceProperty<T>(key, defaultValue, fileName, operatingMode), ReadWriteProperty<Any, T> {

    /**
     * Returns the value of the property for the given SharedPreference.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @return the SharedPreference value.
     */
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return prefs.getValue(getPreferenceKey(property), defaultValue)
    }

    /**
     * Sets the value of the property for the given SharedPreference.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @param value the value to set.
     */
    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        prefs.edit { putValue(getPreferenceKey(property), value) }
    }
}