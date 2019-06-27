package com.chaity.android.easy.move.appConverter;

import androidx.room.TypeConverter;

import com.chaity.android.easy.move.model.Location;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Converters {
    @TypeConverter
    public static Location toLocObject(String value) {
        Type listType = new TypeToken<Location>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
    @TypeConverter
    public static String fromLocationObject(Location location) {
        Gson gson = new Gson();
        String json = gson.toJson(location);
        return json;
    }
}