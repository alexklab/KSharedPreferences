package com.alexklab.ksharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.IntDef

/**
 * Base implementation for [PreferencesHolder]
 *
 * @param preferencesFileName the preferences file name.
 * @param preferencesMode the preferences file operating mode
 */
open class SharedPreferencesHolder(
    preferencesFileName: String = DEFAULT_PREFERENCES_FILE_NAME,
    @PreferencesMode preferencesMode: Int = Context.MODE_PRIVATE
) : PreferencesHolder {

    @IntDef(
        value = [
            Context.MODE_PRIVATE,
            Context.MODE_WORLD_READABLE,
            Context.MODE_WORLD_WRITEABLE,
            Context.MODE_MULTI_PROCESS
        ]
    )
    @Retention(AnnotationRetention.SOURCE)
    annotation class PreferencesMode

    override val prefs: Lazy<SharedPreferences> = lazy {
        ApplicationContextHolder.applicationContext
            .getSharedPreferences(preferencesFileName, preferencesMode)
    }

    private companion object {
        const val DEFAULT_PREFERENCES_FILE_NAME = "prefs"
    }
}