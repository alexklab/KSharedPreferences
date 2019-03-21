package com.alexklab.ksharedpreferences

import android.content.SharedPreferences
import com.nhaarman.mockito_kotlin.any
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class SharedPreferencesGetValueExtensionsTest {

    @Mock
    lateinit var prefs: SharedPreferences

    private companion object {
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
        `when`(prefs.getBoolean(any(), any())).thenReturn(booleanValue)
        `when`(prefs.getInt(any(), any())).thenReturn(intValue)
        `when`(prefs.getLong(any(), any())).thenReturn(longValue)
        `when`(prefs.getFloat(any(), any())).thenReturn(floatValue)
        `when`(prefs.getString(any(), any())).thenReturn(stringValue)
        `when`(prefs.getStringSet(any(), any())).thenReturn(stringSetValue)
    }

    @Test
    fun `getValue(Boolean) should invoke getBoolean and return expected value`() {
        val value = prefs.getValue(key = "", defaultValue = false)
        verify(prefs).getBoolean(any(), any())
        verifyNoMoreInteractions(prefs)
        assertEquals(booleanValue, value)
    }

    @Test
    fun `getValue(Int) should invoke getInt and return expected value`() {
        val value = prefs.getValue(key = "", defaultValue = 123)
        verify(prefs).getInt(any(), any())
        verifyNoMoreInteractions(prefs)
        assertEquals(intValue, value)
    }

    @Test
    fun `getValue(Long) should invoke getLong and return expected value`() {
        val value = prefs.getValue(key = "", defaultValue = 154658L)
        verify(prefs).getLong(any(), any())
        verifyNoMoreInteractions(prefs)
        assertEquals(longValue, value)
    }

    @Test
    fun `getValue(Float) should invoke getFloat and return expected value`() {
        val value = prefs.getValue(key = "", defaultValue = 0.1324f)
        verify(prefs).getFloat(any(), any())
        verifyNoMoreInteractions(prefs)
        assertEquals(floatValue, value)
    }

    @Test
    fun `getValue(String) should invoke getString and return expected value`() {
        val value = prefs.getValue(key = "", defaultValue = "asd")
        verify(prefs).getString(any(), any())
        verifyNoMoreInteractions(prefs)
        assertEquals(stringValue, value)
    }

    @Test
    fun `getValue(StringSet) should invoke getStringSet and return expected value`() {
        val value = prefs.getValue(key = "", defaultValue = setOf("a", "b", "c"))
        verify(prefs).getStringSet(any(), any())
        verifyNoMoreInteractions(prefs)
        assertEquals(stringSetValue, value)
    }

    @Test
    fun `getValue(NotStringSet) should throw exception`() {
        val exception = try {
            prefs.getValue(key = "", defaultValue = setOf("qwe", 1, 3L, 5f))
            null
        } catch (e: Exception) {
            e
        }
        verifyZeroInteractions(prefs)
        assertNotNull(exception)
    }

}