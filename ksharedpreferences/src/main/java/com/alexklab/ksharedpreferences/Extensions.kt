package com.alexklab.ksharedpreferences

import android.content.SharedPreferences

inline fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    action(editor)
    editor.apply()
}

/**
 * Transform string from 'camelCase' style to 'SCREAMING_SNAKE_CASE'
 */
fun String.toScreamingSnakeCase(divider: String = "_"): String {
    if (isEmpty()) return this

    val builder = StringBuilder()

    forEachIndexed { index, char ->
        if (char.isUpperCase()) {
            if (index != 0 && (isLowerCaseOrFalseAt(index + 1) || isLowerCaseOrFalseAt(index - 1))) {
                builder.append(divider)
            }
            builder.append(char)
        } else {
            builder.append(char.toUpperCase())
        }
    }
    return builder.toString()
}

fun String.isLowerCaseOrFalseAt(position: Int): Boolean =
    getOrNull(position)?.isLowerCase() ?: false

@Suppress("UNCHECKED_CAST")
fun <T : Any> SharedPreferences.getValue(key: String, defaultValue: T): T = when (defaultValue) {
    is Boolean -> getBoolean(key, defaultValue) as T
    is Int -> getInt(key, defaultValue) as T
    is Long -> getLong(key, defaultValue) as T
    is Float -> getFloat(key, defaultValue) as T
    is String -> getString(key, defaultValue) as T
    is Set<*> -> if (defaultValue.all { it is String }) {
        getStringSet(key, defaultValue as Set<String>) as T
    } else {
        throw IllegalArgumentException("Unsupported value type: ${defaultValue.javaClass}, only StringSet allowed ")
    }
    else -> throw IllegalArgumentException("Unsupported value type: ${defaultValue.javaClass}")
}

@Suppress("UNCHECKED_CAST")
fun <T : Any> SharedPreferences.Editor.putValue(key: String, value: T): SharedPreferences.Editor = when (value) {
    is Boolean -> putBoolean(key, value)
    is Int -> putInt(key, value)
    is Long -> putLong(key, value)
    is Float -> putFloat(key, value)
    is String -> putString(key, value)
    is Set<*> -> if (value.all { it is String }) {
        putStringSet(key, value as Set<String>)
    } else {
        throw IllegalArgumentException("Unsupported value type: ${value.javaClass}, only StringSet allowed ")
    }
    else -> throw IllegalArgumentException("Unsupported value type: ${value.javaClass}")
}