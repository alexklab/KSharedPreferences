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

    this.forEachIndexed { index, char ->
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