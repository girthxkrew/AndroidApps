package com.android.nycschool.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;

import com.android.nycschool.R;
import com.android.nycschool.UI.ViewModels.SchoolStatsViewModel;

/*
 * This Activities display the information of the school selected by the user in the recycler view
 */

public class SchoolStatsActivity extends AppCompatActivity {
    private SchoolStatsViewModel viewModel;
    private String dbn = "";
    private TextView schoolName, math, writing, criticalReading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_info);
        if(getSupportActionBar()!= null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            dbn = extra.getString("dbn");
        }
        schoolName = findViewById(R.id.name);
        math = findViewById(R.id.math);
        writing = findViewById(R.id.writing);
        criticalReading = findViewById(R.id.critical_reading);
        viewModel = ViewModelProviders.of(this).get(SchoolStatsViewModel.class);
        if(!dbn.isEmpty()) {
            viewModel.getSchoolStatsData(dbn).observe(this, school -> {
                schoolName.setText(school.getSchool_name());
                math.setText("Math: " + school.getSat_math_avg_score());
                writing.setText("Writing: " + school.getSat_critical_reading_avg_score());
                criticalReading.setText("Critical Reading: " + school.getSat_critical_reading_avg_score());
            });
        }



    }
}
