package com.alexklab.ksharedpreferences

import android.content.SharedPreferences
import com.nhaarman.mockito_kotlin.any
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class SharedPreferencesEditorPutValueExtensionsTest {

    @Mock
    lateinit var prefsEditor: SharedPreferences.Editor

    private companion object {
        const val key = "asdfasdfasdfasdf"
        const val booleanValue: Boolean = true
        const val intValue: Int = 101
        const val longValue: Long = 100500L
        const val floatValue: Float = 0.4f
        const val stringValue: String = "qwerty"
        val stringSetValue: Set<String> = setOf("q", "w", "e", "r", "t", "y")
    }

    @Before
    fun beforeEach() {
        MockitoAnnotations.initMocks(this)
        `when`(prefsEditor.putBoolean(any(), any())).thenReturn(prefsEditor)
        `when`(prefsEditor.putInt(any(), any())).thenReturn(prefsEditor)
        `when`(prefsEditor.putLong(any(), any())).thenReturn(prefsEditor)
        `when`(prefsEditor.putFloat(any(), any())).thenReturn(prefsEditor)
        `when`(prefsEditor.putString(any(), any())).thenReturn(prefsEditor)
        `when`(prefsEditor.putStringSet(any(), any())).thenReturn(prefsEditor)
    }

    @Test
    fun `putValue(Boolean) should invoke putBoolean`() {
        prefsEditor.putValue(key, booleanValue)
        verify(prefsEditor).putBoolean(key, booleanValue)
        verifyNoMoreInteractions(prefsEditor)
    }

    @Test
    fun `putValue(Int) should invoke putInt`() {
        prefsEditor.putValue(key, intValue)
        verify(prefsEditor).putInt(key, intValue)
        verifyNoMoreInteractions(prefsEditor)
    }

    @Test
    fun `putValue(Long) should invoke putLong`() {
        prefsEditor.putValue(key, longValue)
        verify(prefsEditor).putLong(key, longValue)
        verifyNoMoreInteractions(prefsEditor)
    }

    @Test
    fun `putValue(Float) should invoke putFloat`() {
        prefsEditor.putValue(key, floatValue)
        verify(prefsEditor).putFloat(key, floatValue)
        verifyNoMoreInteractions(prefsEditor)
    }

    @Test
    fun `putValue(String) should invoke putString`() {
        prefsEditor.putValue(key, stringValue)
        verify(prefsEditor).putString(key, stringValue)
        verifyNoMoreInteractions(prefsEditor)
    }

    @Test
    fun `putValue(StringSet) should invoke putStringSet`() {
        prefsEditor.putValue(key, stringSetValue)
        verify(prefsEditor).putStringSet(key, stringSetValue)
        verifyNoMoreInteractions(prefsEditor)
    }

    @Test
    fun `putValue(NotStringSet) should throw exception`() {
        val exception = try {
            prefsEditor.putValue(key, setOf("qwe", 1, 3L, 5f))
            null
        } catch (e: Exception) {
            e
        }
        verifyZeroInteractions(prefsEditor)
        assertNotNull(exception)
    }

}