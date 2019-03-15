package com.alexklab.ksharedpreferences

class PreferencesContentProvider : SimpleContentProvider() {

    override fun onCreate(): Boolean {
        PreferensesContextHolder.init(context)
        return true
    }

}