package com.alexklab.ksharedpreferences

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
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
) : PreferenceProperty<T>(defaultValue, key),
    ReadOnlyProperty<PreferencesHolder, MutableLiveData<T>> {

    private lateinit var liveData: MutableLiveData<T>

    /**
     * Returns the value of the LivePreference property with registered liveData listener.
     *
     * @param thisRef the [PreferencesHolder] for which the value is requested.
     * @param property the metadata for the property.
     * @return the LivePreference.
     */
    override fun getValue(thisRef: PreferencesHolder, property: KProperty<*>): MutableLiveData<T> {
        if (!::liveData.isInitialized) {
            val prefKey = getPreferenceKey(property)
            val prefs = thisRef.prefs.value
            liveData = newPreferenceMutableLiveData(prefs, prefKey)
            prefs.registerOnSharedPreferenceChangeListener(liveData, prefKey)
        }

        return liveData
    }

    private fun newPreferenceMutableLiveData(
        prefs: SharedPreferences,
        prefKey: String
    ): MutableLiveData<T> =
        object : MutableLiveData<T>(prefs.getValue(prefKey, defaultValue)) {

            override fun postValue(value: T) {
                super.postValue(value)
                updatePrefValue(value)
            }

            override fun setValue(value: T) {
                super.setValue(value)
                updatePrefValue(value)
            }

            @Suppress("USELESS_ELVIS")
            private fun updatePrefValue(value: T) {
                val givenValue = value ?: defaultValue // nullable types not supported
                prefs.edit { putValue(prefKey, givenValue) }
            }
        }

    private fun SharedPreferences.registerOnSharedPreferenceChangeListener(
        data: MutableLiveData<T>,
        prefKey: String
    ) {

        registerOnSharedPreferenceChangeListener { p, k ->
            if (prefKey == k) {
                val prefValue = p.getValue(prefKey, defaultValue)
                if (prefValue != data.value) {
                    data.value = prefValue
                }
            }
        }
    }
}