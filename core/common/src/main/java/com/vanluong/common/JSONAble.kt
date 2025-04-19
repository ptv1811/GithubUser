package com.vanluong.common

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONObject

/**
 * Created by van.luong
 * on 20,April,2025
 */
interface JSONAble {
    fun <T> JSONAble.toJSON(clazz: Class<T>): JSONObject? {
        if (!clazz.isInstance(this)) return null
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter = moshi.adapter(clazz)
        val jsonString = adapter.toJson(clazz.cast(this))
        return JSONObject(jsonString)
    }

    fun <T> JSONAble.toJSONString(clazz: Class<T>): String? {
        return toJSON(clazz)?.toString(4) ?: this.toString()
    }
}

inline fun <reified T> String.fromJson(): T? {
    if (this.isEmpty()) {
        return null
    }

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val adapter = moshi.adapter(T::class.java)
    return adapter.fromJson(this)
}