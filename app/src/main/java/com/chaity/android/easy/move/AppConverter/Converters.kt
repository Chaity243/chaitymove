package com.chaity.android.easy.move.AppConverter

import androidx.room.TypeConverter

import com.chaity.android.easy.move.model.Location
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.lang.reflect.Type

object Converters {
    @TypeConverter
    fun toLocObject(value: String): Location? {
        val listType = object : TypeToken<Location>() {

        }.type
        return Gson().fromJson<Location>(value, listType)
    }

    @TypeConverter
    fun fromLocationObject(location: Location): String {
        val gson = Gson()
        return gson.toJson(location)
    }
}