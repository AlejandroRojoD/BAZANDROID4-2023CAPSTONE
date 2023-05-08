package com.example.local.entities

import androidx.room.TypeConverter

class IntegerListConverter {
    @TypeConverter
    fun fromList(list: List<Int>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toList(str: String): List<Int> {
        return str.split(",").map { it.toInt() }
    }
}