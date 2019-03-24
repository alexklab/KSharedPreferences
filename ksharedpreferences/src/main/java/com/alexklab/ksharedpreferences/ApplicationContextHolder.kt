package com.alexklab.ksharedpreferences

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import java.lang.ref.WeakReference

object ApplicationContextHolder {

    private var applicationContextRef: WeakReference<Context>? = null

    val applicationContext: Context
        get() = applicationContextRef?.get()
            ?: getApplicationContextWithReflection()
            ?: throw IllegalStateException("Initialization required: context undefined")

    fun init(context: Context?) {
        context
            ?.applicationContext
            ?.let { applicationContextRef = WeakReference(it) }
    }

    @SuppressLint("PrivateApi")
    private fun getApplicationContextWithReflection(): Context? {
        // Fallback, should only run once per non default process.
        val activityThread = Class.forName("android.app.ActivityThread")
        val context = activityThread
            .getDeclaredMethod("currentApplication")
            .invoke(null) as? Context

        Log.w("PreferencesContext", "Initialization via reflect API. $context")
        init(context)

        return context
    }
}