package tr.mobileapp.Database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import java.sql.Date;

import tr.mobileapp.Entity.Role;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Role fromJsonRole(String jsonRole) {
        Gson gson = new Gson();
        return gson.fromJson(jsonRole, Role.class);
    }

    @TypeConverter
    public static String roleToJsonRole(Role role) {
        return new Gson().toJson(role);
    }
}
