package com.alexklab.ksharedpreferences

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

abstract class SimpleContentProvider : ContentProvider() {

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw IllegalStateException("Method 'insert' not implemented")
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        throw IllegalStateException("Method 'query' not implemented")
    }

    override fun onCreate(): Boolean {
        throw IllegalStateException("Method 'onCreate' not implemented")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        throw IllegalStateException("Method 'update' not implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        throw IllegalStateException("Method 'delete' not implemented")
    }

    override fun getType(uri: Uri): String? {
        throw IllegalStateException("Method 'getType' not implemented")
    }

}