package com.vanluong.common

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONObject

/**
 * Created by van.luong
 * on 20,April,2025
 *
 * This interface provides a method to convert an object to JSON format.
 * Used for a better-visually logging purpose.
 */
interface JSONAble {
    /**
     * Converts the current object to a JSON object.
     *
     * @param clazz The class of the object to convert.
     */
    fun <T> toJSON(clazz: Class<T>): JSONObject? {
        if (!clazz.isInstance(this)) return null
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter = moshi.adapter(clazz)
        val jsonString = adapter.toJson(clazz.cast(this))
        return JSONObject(jsonString)
    }

    /**
     * Converts the current object to a JSON string.
     *
     * @param clazz The class of the object to convert.
     */
    fun <T> toJSONString(clazz: Class<T>): String? {
        return toJSON(clazz)?.toString(4)
    }
}

/**
 * Converts a JSON string to an object of the specified type.
 *
 * @param T The type of the object to convert to.
 * @return The converted object, or null if the conversion fails.
 */
inline fun <reified T> String.fromJson(): T? {
    if (this.isEmpty()) {
        return null
    }

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val adapter = moshi.adapter(T::class.java)
    return try {
        adapter.fromJson(this)
    } catch (e: Exception) {
        null
    }
}