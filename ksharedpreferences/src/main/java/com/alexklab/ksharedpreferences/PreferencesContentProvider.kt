package com.alexklab.ksharedpreferences

class PreferencesContentProvider : SimpleContentProvider() {

    override fun onCreate(): Boolean {
        PreferencesContextHolder.init(context)
        return true
    }

}