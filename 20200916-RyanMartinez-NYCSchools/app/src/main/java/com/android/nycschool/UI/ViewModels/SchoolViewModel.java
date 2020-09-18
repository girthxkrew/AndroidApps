package com.android.nycschool.UI.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.nycschool.Data.Model.School;
import com.android.nycschool.Data.Repository.SchoolRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

/*
 * This class creates the view model that is used by the school activity
 * This class is in charge of all database and network calls
 * One thing I would have changed would have been adding a splash screen to make the network calls
 * in the beginning so the user would not have had to wait for the recyclerview to load
 * I also would have lotti to display a waiting animation for the user to view on the splash screen
 * Custom animation or message if the database and network calls both failed to retrieve data would have been something I would have added
 */

public class SchoolViewModel extends AndroidViewModel {
    private SchoolRepository schoolRepository;
    private MutableLiveData<List<School>> liveDataSchools;

    public SchoolViewModel(@NonNull Application application) {
        super(application);
        schoolRepository = SchoolRepository.getInstance(application.getApplicationContext());
    }

    public LiveData<List<School>> getSchoolData() {
        if (liveDataSchools == null) {
            liveDataSchools = new MutableLiveData<>();
            getSchools();
        }
        return liveDataSchools;
    }

    private void getSchools() {
        schoolRepository.getSchoolsFromDatabase().take(1).filter(list -> !list.isEmpty())
                .switchIfEmpty(schoolRepository.getSchools()).doOnNext(schools -> {
            liveDataSchools.postValue(schools);
            schoolRepository.saveSchools(schools);
        }).observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResults, this::handleError);
    }

    private void handleError(Throwable throwable) {
        Log.d("error", throwable.getMessage());
    }

    private void handleResults(Object o) {
        Log.d("Success", o.toString());
    }


}
