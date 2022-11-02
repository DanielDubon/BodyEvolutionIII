package com.UI;

import static com.UI.SigninActivity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainMenu extends AppCompatActivity {
    private TextView tvNombreUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        tvNombreUsuario = findViewById(R.id.nombreusuario);
        tvNombreUsuario.setText("Bienvenido "+user.getName());


    }

    public void workout(View view){
        Intent intent  = new Intent(this, EvolveUI.class);
        startActivity(intent);

    }
    /*
    //BOTONES PENDIENTES DE PROGRAMAR: RECETAS/ANALISIS DE SALUD
    public void workout(View view){
        Intent intent  = new Intent(this, EvolveUI.class);
        startActivity(intent);

    }
    public void workout(View view){
        Intent intent  = new Intent(this, EvolveUI.class);
        startActivity(intent);



 */
}