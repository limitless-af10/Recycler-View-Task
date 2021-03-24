package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.test.fragments.QueryFragment;

public class Week6TaskStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week6_task_start);

        if (savedInstanceState == null) {
            QueryFragment queryFragment = QueryFragment.newInstance("","");
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, queryFragment, "query")
                    .setPrimaryNavigationFragment(queryFragment)
                    .commit();
        }
    }
}