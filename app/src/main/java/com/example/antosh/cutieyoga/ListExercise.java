package com.example.antosh.cutieyoga;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.antosh.cutieyoga.Adapter.RecyclerViewAdapter;
import com.example.antosh.cutieyoga.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ListExercise extends AppCompatActivity {

    List<Exercise> exercises = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercise);

        initData();
        recyclerView = findViewById(R.id.list_ex);
        adapter = new RecyclerViewAdapter(exercises,getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    //TODO RENAME POSE NAMES !!!
    private void initData() {
        exercises.add(new Exercise(R.drawable.maditationpose,"Meditation pose"));
        exercises.add(new Exercise(R.drawable.babypose_,"Baby pose"));
        exercises.add(new Exercise(R.drawable.test01,"Tree pose"));
        exercises.add(new Exercise(R.drawable.bowpose,"Bow pose"));
        exercises.add(new Exercise(R.drawable.trianglepose,"Triangle pose"));
        exercises.add(new Exercise(R.drawable.tigerpose,"Tiger pose"));
        exercises.add(new Exercise(R.drawable.wheelpose,"Wheel pose"));
        exercises.add(new Exercise(R.drawable.halfspinaltwist,"Half Spinal Twist pose"));
        exercises.add(new Exercise(R.drawable.mountainpose,"Mountain pose"));
        exercises.add(new Exercise(R.drawable.boundanglepose,"Bound Angle pose"));
        exercises.add(new Exercise(R.drawable.warriorpose,"Warrior pose"));
        exercises.add(new Exercise(R.drawable.firelogpose,"Fire Log pose"));
        exercises.add(new Exercise(R.drawable.cocktailpose11,"Cocktail pose"));
        exercises.add(new Exercise(R.drawable.cobrapose,"Cobra pose"));
        exercises.add(new Exercise(R.drawable.fallingstarpose,"Falling Star pose"));




    }
}
