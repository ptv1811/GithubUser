package com.vanluong.common

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * Created by van.luong
 * on 22,April,2025
 */
class ResultTest {
    @Test
    fun `test Success result`() {
        val successResult = Result.Success("Test Data")
        assertEquals("Test Data", successResult.data)
        assertEquals("Success[data=Test Data]", successResult.toString())
    }

    @Test
    fun `test Loading result`() {
        val loadingResult = Result.Loading<String>()
        assertEquals(null, loadingResult.data)
        assertEquals("Loading", loadingResult.toString())
    }

    @Test
    fun `test DataError result`() {
        val errorMessage = "An error occurred"
        val dataErrorResult = Result.DataError<String>(errorMessage)
        assertEquals(null, dataErrorResult.data)
        assertEquals(errorMessage, dataErrorResult.errorMessage)
        assertEquals("Error[exception=An error occurred]", dataErrorResult.toString())
    }

    @Test
    fun `test NetworkError result`() {
        val errorResponse = ErrorResponse(404, "Not Found")
        val networkErrorResult = Result.NetworkError<String>(errorResponse)
        assertEquals(null, networkErrorResult.data)
        assertEquals(errorResponse, networkErrorResult.errorResponse)
        assertEquals("NetworkError[code=404, message=Not Found]", networkErrorResult.toString())
    }
}