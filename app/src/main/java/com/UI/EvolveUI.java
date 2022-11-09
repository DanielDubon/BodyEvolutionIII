package com.UI;

import static com.UI.SigninActivity.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
        System.out.println("ID DE USUARIO ACTUAL "+user.getId());


    }

    public void Actualworkout(){

        tvEjercicios.setText(workout.getExcersise());
        tvSets.setText(""+user.getSets());
        tvReps.setText(""+user.getReps());


    }

    public void terminar(View view){
        Actualworkout();
        workout.setSets(workout.getSets()+1);
        System.out.println("WORKOUT SETS:"+workout.getSets());


        if (workout.getSets() == 7) {
            AlertDialog.Builder builder = new AlertDialog.Builder(EvolveUI.this);
            builder.setMessage("¡Rutina terminada por hoy!");
            builder.setPositiveButton("!Quiero hacer mas ejercicio¡", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                   user.setIndexofBS(user.getIndexofBS()+1);

                }
            });

            builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(EvolveUI.this, MainMenu.class);
                    startActivity(intent);
                    Toast.makeText(EvolveUI.this, "¡Buen trabajo por hoy!", Toast.LENGTH_SHORT).show();
                    user.setIndexofBS(user.getIndexofBS()+1);
                }
            });



            if (!(user.getHealth() == 100)) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "UsersBodyEvolution", null, 1);
                SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
                if (!(user.getHealth() >= 96)) {
                    user.setHealth(user.getHealth() + 5);
                } else {
                    user.setHealth(100);
                }



                BaseDeDatos.execSQL("Update currentusers set estadosalud ="+user.getHealth()+" where id ="+user.getId());

                BaseDeDatos.close();
                Toast.makeText(this, "¡Tu estado de salud aumento!, actual:"+user.getHealth(), Toast.LENGTH_SHORT).show();
            }
            builder.show();
            workout.setSets(0);
        }

    }


}