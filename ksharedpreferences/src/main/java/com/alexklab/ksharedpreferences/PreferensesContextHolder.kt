package com.alexklab.ksharedpreferences

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import java.lang.ref.WeakReference

object PreferensesContextHolder {

    private var contextRef: WeakReference<Context>? = null

    val context: Context
        get() = contextRef?.get()
            ?: initAndGetContextWithReflection()
            ?: throw IllegalStateException("Initialization required: context undefined")

    fun init(context: Context?) {
        context
            ?.applicationContext
            ?.let { contextRef = WeakReference(it) }
    }

    @SuppressLint("PrivateApi")
    private fun initAndGetContextWithReflection(): Context? {
        // Fallback, should only run once per non default process.
        val activityThread = Class.forName("android.app.ActivityThread")
        val applicationContext = activityThread
            .getDeclaredMethod("currentApplication")
            .invoke(null) as? Context

        Log.w("PreferensesContextHolder", "Initialization via reflect API. $applicationContext")
        init(applicationContext)

        return applicationContext
    }
}