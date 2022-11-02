package com.UI;

import static com.UI.SigninActivity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Model.Workout;

public class EvolveUI extends AppCompatActivity {

    private TextView tvEjercicios, tvReps, tvSets;
    private Button btnTerminar;
    Workout workout = new Workout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evolve_ui);

        tvEjercicios =findViewById(R.id.tvEjercicio);
        tvReps =findViewById(R.id.tvReps);
        tvSets =findViewById(R.id.tvSets);
        btnTerminar = findViewById(R.id.btnTerminado);
        Actualworkout();



    }

    public void Actualworkout(){

        tvEjercicios.setText(workout.getExcersise());
        tvSets.setText(""+user.getSets());
        tvReps.setText(""+user.getReps());


    }

    public void terminar(View view){
        Actualworkout();

    }


}