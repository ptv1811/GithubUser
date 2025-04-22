package com.vanluong.common

/**
 * Created by van.luong
 * on 20,April,2025
 */
sealed class Result<T>(
    val data: T? = null,
    val errorMessage: String? = null,
) {
    class Success<T>(data: T) : Result<T>(data)
    class Loading<T>(data: T? = null) : Result<T>(data)
    class DataError<T>(errorMessage: String) : Result<T>(null, errorMessage)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is DataError -> "Error[exception=$errorMessage]"
            is Loading<T> -> "Loading"
        }
    }
}