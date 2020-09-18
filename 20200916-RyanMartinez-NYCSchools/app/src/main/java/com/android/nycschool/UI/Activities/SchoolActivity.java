package com.android.nycschool.UI.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.nycschool.Data.Model.School;
import com.android.nycschool.R;
import com.android.nycschool.UI.RecyclerView.SchoolAdapter;
import com.android.nycschool.UI.ViewModels.SchoolViewModel;

/*
 * Main screen for app that contains a recyclerview to display a list of schools
 * that the user can choose from
 * Changes I would have made would have been to use fragments to display the recyclerview
 * and new screen that show the school's sat scores
 * For this project I used Activities due to knowledge and experience
 */

public class SchoolActivity extends AppCompatActivity {

    private SchoolViewModel viewModel;
    private RecyclerView recyclerView;
    private SchoolAdapter schoolAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(R.string.title);
        recyclerView = findViewById(R.id.recyclerView);
        viewModel = ViewModelProviders.of(this).get(SchoolViewModel.class);
        viewModel.getSchoolData().observe(this, schools -> {
            schoolAdapter = new SchoolAdapter(schools, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(schoolAdapter);
        });
    }
}
