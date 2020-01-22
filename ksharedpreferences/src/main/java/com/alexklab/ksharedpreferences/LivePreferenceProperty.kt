package com.alexklab.ksharedpreferences

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Class that can be used for implementing property delegates of LivePreferences.
 * Allowed types: Boolean, Int, Long, Float, String, Set<String>
 *
 * @param T the type of the preference value.
 * @param key the custom preference key, if this param is null then key will be generated from property name
 * @param defaultValue the preference default value
 */
class LivePreferenceProperty<T : Any>(
    defaultValue: T,
    key: String? = null
) : PreferenceProperty<T>(defaultValue, key), ReadOnlyProperty<PreferencesHolder, LivePreference<T>> {

    private lateinit var livePreference: LivePreference<T>

    /**
     * Returns the value of the LivePreference property with registered liveData listener.
     *
     * @param thisRef the [PreferencesHolder] for which the value is requested.
     * @param property the metadata for the property.
     * @return the LivePreference.
     */
    override fun getValue(thisRef: PreferencesHolder, property: KProperty<*>): LivePreference<T> {
        if (::livePreference.isInitialized) {
            livePreference.registerOnSharedPreferenceChangeListener()
        } else {
            livePreference = LivePreference(
                key = getPreferenceKey(property),
                defaultValue = defaultValue,
                prefs = thisRef.prefs
            )
        }
        return livePreference
    }
}