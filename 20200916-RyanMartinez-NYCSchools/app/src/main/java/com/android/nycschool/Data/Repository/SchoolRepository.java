package com.android.nycschool.Data.Repository;

import android.content.Context;

import com.android.nycschool.Data.Database.RoomDataSource;
import com.android.nycschool.Data.Model.School;
import com.android.nycschool.Data.Model.SchoolStats;
import com.android.nycschool.Data.Network.RetrofitDataSource;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/*
 * This class is the main used to access both network and database calls
 * from a single source
 */

public class SchoolRepository implements SchoolDataSource {

    private static SchoolRepository instance = null;
    private RetrofitDataSource retrofitDataSource;
    private RoomDataSource roomDataSource;

    private SchoolRepository(Context context) {
        retrofitDataSource = RetrofitDataSource.getInstance();
        roomDataSource = RoomDataSource.getInstance(context);
    }

    public static SchoolRepository getInstance(Context context) {
        if(instance == null) {
            instance = new SchoolRepository(context);
        }
        return instance;
    }
    @Override
    public Observable<List<School>> getSchools() {
        return retrofitDataSource.getSchools();
    }

    @Override
    public Observable<Void> saveSchools(List<School> schools) {
        return roomDataSource.saveSchools(schools);
    }

    @Override
    public Observable<List<School>> getSchoolsFromDatabase() {
        return roomDataSource.getSchoolsFromDatabase();
    }

    @Override
    public Observable<List<SchoolStats>> getSchoolStats(String dbn) {
        return retrofitDataSource.getSchoolStats(dbn);
    }

    @Override
    public Observable<Void> saveSchoolStats(List<SchoolStats> schoolStats) {
        return roomDataSource.saveSchoolStats(schoolStats);
    }

    @Override
    public Observable<List<SchoolStats>> getSchoolStatsFromDatabase(String dbn) {
        return roomDataSource.getSchoolStatsFromDatabase(dbn);
    }

    @Override
    public Observable<List<SchoolStats>> getAllSchoolStats() {
        return roomDataSource.getAllSchoolStats();
    }

}
