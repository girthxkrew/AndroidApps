package com.android.nycschool.Data.Network;

import com.android.nycschool.Data.Model.School;
import com.android.nycschool.Data.Model.SchoolStats;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 * This interface represents all the API calls
 */

public interface SchoolAPI {

    @GET("s3k6-pzi2.json?$select=dbn, school_name")
    Observable<List<School>> getSchools();

    @GET("f9bf-2cp4.json")
    Observable<List<SchoolStats>> getSchoolStats(@Query("dbn") String dbn);
}
