package com.android.nycschool.Data.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.android.nycschool.Data.Model.School;
import com.android.nycschool.Data.Model.SchoolStats;

/*
 * This class is used to create the database and handle all database
 * related functionality
 */

@Database(entities = {School.class, SchoolStats.class}, version = 1)
public abstract class SchoolRoomDatabase extends RoomDatabase {
    private static SchoolRoomDatabase instance = null;

    public abstract SchoolDAO getSchoolDAO();
    public abstract SchoolStatsDAO getSchoolStatsDAO();

    public static SchoolRoomDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), SchoolRoomDatabase.class, "schoolDatabase").build();
        }

        return instance;
    }
}
