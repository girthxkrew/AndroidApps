package com.android.nycschool.UI.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.nycschool.Data.Model.School;
import com.android.nycschool.R;
import com.android.nycschool.UI.Activities.SchoolStatsActivity;

import java.util.List;

/*
 * This class contains the adapter that will be used by the recycler view
 */

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.ViewHolder> {
    private List<School> schoolList;
    private Context context;

    public SchoolAdapter(List<School> schools, Context context) {
        schoolList = schools;
        this.context = context;
    }
    @NonNull
    @Override
    public SchoolAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View schoolItemView = layoutInflater.inflate(R.layout.recycler_view_item, parent, false);
        ViewHolder holder = new ViewHolder(schoolItemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        School school = schoolList.get(position);
        TextView schoolName = holder.schoolName;
        schoolName.setText(school.getSchool_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SchoolStatsActivity.class);
                intent.putExtra("dbn", school.getDbn());
                intent.putExtra("name", school.getSchool_name());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return schoolList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView schoolName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            schoolName = itemView.findViewById(R.id.school_name);
        }
    }
}
