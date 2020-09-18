package com.android.nycschool.Data.Repository;

import com.android.nycschool.Data.Model.School;
import com.android.nycschool.Data.Model.SchoolStats;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/*
 * This is the interface that represents all database and network calls
 * This interface is based on the repository design pattern
 * I really would have liked to used Dagger to help with Dependency Injection
 * I do not have a lot of experience in Dagger but would consider it a viable option
 * to remove some of the boiler plate code from this project
 */

public interface SchoolDataSource {

    Observable<List<School>> getSchools();

    Observable<Void> saveSchools(List<School> schools);

    Observable<List<School>> getSchoolsFromDatabase();

    Observable<List<SchoolStats>> getSchoolStats(String dbn);

    Observable<Void> saveSchoolStats(List<SchoolStats> schoolStats);

    Observable<List<SchoolStats>> getSchoolStatsFromDatabase(String dbn);

    Observable<List<SchoolStats>> getAllSchoolStats();

}
