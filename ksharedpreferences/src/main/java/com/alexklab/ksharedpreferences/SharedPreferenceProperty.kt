package com.alexklab.ksharedpreferences

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


/**
 * Class that can be used for implementing property delegates of SharedPreference values.
 * Allowed types: Boolean, Int, Long, Float, String, Set<String>
 *
 * @param T the type of the preference value.
 * @param defaultValue the preference default value
 * @param key the custom preference key, if this param is null then key will be generated from property name
 */
open class SharedPreferenceProperty<T : Any>(
    defaultValue: T,
    key: String? = null
) : PreferenceProperty<T>(defaultValue, key), ReadWriteProperty<PreferencesHolder, T> {

    /**
     * Returns the value of the property for the given SharedPreference.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @return the SharedPreference value.
     */
    override fun getValue(thisRef: PreferencesHolder, property: KProperty<*>): T {
        return thisRef.prefs.value.getValue(getPreferenceKey(property), defaultValue)
    }

    /**
     * Sets the value of the property for the given SharedPreference.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @param value the value to set.
     */
    override fun setValue(thisRef: PreferencesHolder, property: KProperty<*>, value: T) {
        thisRef.prefs.value.edit { putValue(getPreferenceKey(property), value) }
    }
}