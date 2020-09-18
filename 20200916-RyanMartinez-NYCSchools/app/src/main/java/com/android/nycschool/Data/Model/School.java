package com.android.nycschool.Data.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
 * This class is the object representation of how the school table in the database will look
 * Each field represents a column in the table
 * This class is also used to handle the retrofit calls and return the data I thought would work better
 * since the original dataset had a handful of fields
 * This class represents the pojo that will created to show to data will be converted from JSON to a java object
 * I could have used for the school information such as the school phone number as an example or the school's description
 * to make the UI more exciting and insightful but chose to keep it simple for the project
 */

@Entity(tableName = "schools")
public class School {
    @PrimaryKey
    @NonNull
    private String dbn;

    private String school_name;

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }
}