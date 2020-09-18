package com.android.nycschool.Data.Network;

import com.android.nycschool.Data.Model.School;
import com.android.nycschool.Data.Model.SchoolStats;
import com.android.nycschool.Data.Repository.SchoolDataSource;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/*
 * This class encapsulates all network calls
 */

public class RetrofitDataSource implements SchoolDataSource {
    private static RetrofitDataSource instance = null;
    private SchoolAPI schoolAPI;

    private RetrofitDataSource () {
        schoolAPI = RetrofitInstance.getInstance().create(SchoolAPI.class);
    }

    public static RetrofitDataSource getInstance() {
        if(instance == null) {
            instance = new RetrofitDataSource();
        }
        return instance;
    }
    @Override
    public Observable<List<School>> getSchools() {
        return schoolAPI.getSchools();
    }

    @Override
    public Observable<Void> saveSchools(List<School> schools) {
        return null;
    }

    @Override
    public Observable<List<School>> getSchoolsFromDatabase() {
        return null;
    }

    @Override
    public Observable<List<SchoolStats>> getSchoolStats(String dbn) {
        return schoolAPI.getSchoolStats(dbn);
    }

    @Override
    public Observable<Void> saveSchoolStats(List<SchoolStats> schoolStats) {
        return null;
    }

    @Override
    public Observable<List<SchoolStats>> getSchoolStatsFromDatabase(String dbn) {
        return null;
    }

    @Override
    public Observable<List<SchoolStats>> getAllSchoolStats() {
        return null;
    }
}
