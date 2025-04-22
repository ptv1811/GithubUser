package com.vanluong.common

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by van.luong
 * on 22,April,2025
 */
data class TestData(
    val id: Int,
    val name: String
) : JSONAble

@RunWith(RobolectricTestRunner::class)
class JSONAbleTest {
    private val testData = TestData(1, "Test")

    @Test
    fun `toJSON should return JSONObject`() {
        val jsonObject = testData.toJSON(TestData::class.java)
        assert(jsonObject != null)
        assert(jsonObject?.getInt("id") == 1)
        assert(jsonObject?.getString("name") == "Test")
    }

    @Test
    fun `test toJSON with invalid class`() {
        val jsonObject = testData.toJSON(String::class.java)
        assertNull(jsonObject)
    }

    @Test
    fun `test toJSONString with valid class`() {
        val jsonString = testData.toJSONString(TestData::class.java)

        assertNotNull(jsonString)
        assertTrue(jsonString!!.contains("\"id\": 1"))
        assertTrue(jsonString.contains("\"name\": \"Test\""))
    }

    @Test
    fun `test toJSONString with invalid class`() {
        val jsonString = testData.toJSONString(String::class.java)
        assertNull(jsonString)
    }

    @Test
    fun `test fromJson with valid JSON string`() {
        val jsonString = """{"id": 1, "name": "Test Name"}"""
        val testData = jsonString.fromJson<TestData>()

        assertNotNull(testData)
        assertEquals(1, testData?.id)
        assertEquals("Test Name", testData?.name)
    }

    @Test
    fun `test fromJson with invalid JSON string`() {
        val jsonString = """{"invalid": "data"}"""
        val testData = jsonString.fromJson<TestData>()

        assertNull(testData)
    }

    @Test
    fun `test fromJson with empty JSON string`() {
        val jsonString = ""
        val testData = jsonString.fromJson<TestData>()

        assertNull(testData)
    }
}