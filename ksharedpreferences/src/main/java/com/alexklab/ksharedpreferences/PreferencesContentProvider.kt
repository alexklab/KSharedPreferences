package com.alexklab.ksharedpreferences

class PreferencesContentProvider : SimpleContentProvider() {

    override fun onCreate(): Boolean {
        ApplicationContextHolder.init(context)
        return true
    }

}