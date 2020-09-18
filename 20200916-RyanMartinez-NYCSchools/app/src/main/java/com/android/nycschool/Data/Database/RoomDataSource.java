package com.android.nycschool.Data.Database;

import android.content.Context;

import com.android.nycschool.Data.Model.School;
import com.android.nycschool.Data.Model.SchoolStats;
import com.android.nycschool.Data.Repository.SchoolDataSource;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

/*
 * This class is used to encapsulate all database calls
 */

public class RoomDataSource implements SchoolDataSource {
    private static RoomDataSource instance = null;
    private SchoolRoomDatabase roomDatabase;

    private RoomDataSource(Context context) {
        roomDatabase = SchoolRoomDatabase.getInstance(context);
    }

    public static RoomDataSource getInstance(Context context) {
        if(instance == null) {
            instance = new RoomDataSource(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public Observable<List<School>> getSchools() {
        return null;
    }

    @Override
    public Observable<Void> saveSchools(List<School> schools) {
        return Completable.fromAction(() -> {
            roomDatabase.getSchoolDAO().insertAll(schools);
        }).toObservable();
    }

    @Override
    public Observable<List<School>> getSchoolsFromDatabase() {
        return roomDatabase.getSchoolDAO().getSchools();
    }

    @Override
    public Observable<List<SchoolStats>> getSchoolStats(String dbn) {
        return null;
    }


    @Override
    public Observable<Void> saveSchoolStats(List<SchoolStats> schoolStats) {
        return Completable.fromAction(() -> {
           roomDatabase.getSchoolStatsDAO().insertAll(schoolStats);
        }).toObservable();
    }

    @Override
    public Observable<List<SchoolStats>> getSchoolStatsFromDatabase(String dbn) {
        return roomDatabase.getSchoolStatsDAO().getSchoolStats(dbn);
    }

    @Override
    public Observable<List<SchoolStats>> getAllSchoolStats() {
        return roomDatabase.getSchoolStatsDAO().getAllSchoolStats();
    }
}
