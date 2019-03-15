package com.alexklab.ksharedpreferences

import org.junit.Assert.assertEquals
import org.junit.Test

class ExtensionsKtTest {

    @Test
    fun `toScreamingSnakeCase(camelCaseValue) should return CAMEL_CASE_VALUE`() {
        val value = "camelCaseValue"
        val expected = "CAMEL_CASE_VALUE"
        assertEquals(expected, value.toScreamingSnakeCase())
    }

    @Test
    fun `toScreamingSnakeCase(someGFDBValue) should return SOME_GFDB_VALUE`() {
        val value = "someGFDBValue"
        val expected = "SOME_GFDB_VALUE"
        assertEquals(expected, value.toScreamingSnakeCase())
    }

    @Test
    fun `toScreamingSnakeCase(GFDBValue) should return GFDB_VALUE`() {
        val value = "GFDBValue"
        val expected = "GFDB_VALUE"
        assertEquals(expected, value.toScreamingSnakeCase())
    }

    @Test
    fun `toScreamingSnakeCase(someGFDB) should return SOME_GFDB`() {
        val value = "someGFDB"
        val expected = "SOME_GFDB"
        assertEquals(expected, value.toScreamingSnakeCase())
    }

    @Test
    fun `toScreamingSnakeCase(aaa) should return AAA`() {
        val value = "aaa"
        val expected = "AAA"
        assertEquals(expected, value.toScreamingSnakeCase())
    }

    @Test
    fun `toScreamingSnakeCase("") should return ""`() {
        val value = ""
        val expected = ""
        assertEquals(expected, value.toScreamingSnakeCase())
    }

    @Test
    fun `toScreamingSnakeCase(customDivider,"-*-") should return CUSTOM-*-DIVIDER`() {
        val value = "customDivider"
        val divider = "-*-"
        val expected = "CUSTOM-*-DIVIDER"
        assertEquals(expected, value.toScreamingSnakeCase(divider))
    }
}