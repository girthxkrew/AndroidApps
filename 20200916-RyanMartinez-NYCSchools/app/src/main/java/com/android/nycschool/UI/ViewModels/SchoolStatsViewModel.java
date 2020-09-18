package com.android.nycschool.UI.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.nycschool.Data.Model.SchoolStats;
import com.android.nycschool.Data.Repository.SchoolRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

/*
 * This class creates the view model that is used by the schoolstats activity
 * This class is in charge of all database and network calls
 * Due to my concerns with how I should go about retrieving and saving the network calls
 * I was only able to get the network call to work
 * I would have also liked to add a special message if the database or the network calls were both to fail
 * I would have also liked to use a third party such a glimmer to give the user an animation to view
 * while the network call is being made and they are waiting
 * Another option would have been to display a progress bar and I would have used lotti to display the progress bar
 * I try to create any animations myself because I'm an amateur graphic design artist but the process to create one
 * would have taken me awhile
 * So that why I elected not to create a waiting animation such a progress bar for this project
 */

public class SchoolStatsViewModel extends AndroidViewModel {
    private MutableLiveData<SchoolStats> schoolStatsMutableLiveData;
    private SchoolRepository schoolRepository;

    public SchoolStatsViewModel(@NonNull Application application) {
        super(application);
        schoolRepository = SchoolRepository.getInstance(application.getApplicationContext());
    }

    public MutableLiveData<SchoolStats> getSchoolStatsData(String dbn) {
        if (schoolStatsMutableLiveData == null) {
            schoolStatsMutableLiveData = new MutableLiveData<>();
            getSchoolStats(dbn);
        }
        return schoolStatsMutableLiveData;
    }

    private void getSchoolStats(String dbn) {
        schoolRepository.getSchoolStats(dbn).observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResults, this::error);
    }

    private void error(Throwable throwable) {
        Log.d("Error", throwable.getMessage());
    }

    private void handleResults(List<SchoolStats> schoolStats) {
        schoolStatsMutableLiveData.postValue(schoolStats.get(0));
    }

}
