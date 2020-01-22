package com.alexklab.ksharedpreferences

import kotlin.reflect.KProperty

/**
 * Base class for preference property
 *
 * @param defaultValue the preference default value
 * @param preferenceKey the custom preference key, if this param is null then key will be generated from property name
 */
abstract class PreferenceProperty<T>(
    val defaultValue: T,
    private val preferenceKey: String? = null
) {

    private lateinit var preferencePropertyKey: String

    /**
     * Returns the preference key.
     * If custom key is null than returns the property name in SCREAMING_SNAKE_CASE style
     *
     * @param property the metadata for the property.
     * @return the preference key
     */
    protected fun getPreferenceKey(property: KProperty<*>): String {
        if (!::preferencePropertyKey.isInitialized) {
            preferencePropertyKey = preferenceKey ?: property.name.toScreamingSnakeCase()
        }

        return preferencePropertyKey
    }

}