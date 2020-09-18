package com.android.nycschool.Data.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
 * This class is the object representation of how the school_info table in the database will look
 * Each field represents a column in the table
 * This class is also used to handle the retrofit calls and the pojo that will represent how to data will be converted from JSON to a java object
 */

@Entity(tableName = "school_info")
public class SchoolStats {

        @PrimaryKey
        @NonNull
        private String dbn;
        private String school_name;
        private String num_of_sat_test_takers;
        private String sat_critical_reading_avg_score;
        private String sat_math_avg_score;
        private String sat_writing_avg_score;

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

    public String getNum_of_sat_test_takers() {
        return num_of_sat_test_takers;
    }

    public void setNum_of_sat_test_takers(String num_of_sat_test_takers) {
        this.num_of_sat_test_takers = num_of_sat_test_takers;
    }

    public String getSat_critical_reading_avg_score() {
        return sat_critical_reading_avg_score;
    }

    public void setSat_critical_reading_avg_score(String sat_critical_reading_avg_score) {
        this.sat_critical_reading_avg_score = sat_critical_reading_avg_score;
    }

    public String getSat_math_avg_score() {
        return sat_math_avg_score;
    }

    public void setSat_math_avg_score(String sat_math_avg_score) {
        this.sat_math_avg_score = sat_math_avg_score;
    }

    public String getSat_writing_avg_score() {
        return sat_writing_avg_score;
    }

    public void setSat_writing_avg_score(String sat_writing_avg_score) {
        this.sat_writing_avg_score = sat_writing_avg_score;
    }
}
