package com.example.findjob.controller

object Session {
    private val data: MutableMap<String, Any> = mutableMapOf()

    fun set(key: String, value: Any) {
        data[key] = value
    }

    operator fun get(key: String): Any? {
        return data[key]
    }

    fun clear() {
        data.clear()
    }
}