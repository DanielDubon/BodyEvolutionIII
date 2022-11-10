package com.UI;

import static com.UI.SigninActivity.user;

import static Controller.Administrator.processcheck;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DataCheck extends AppCompatActivity {

    private EditText etPeso, etAltura;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_check);

        etPeso = findViewById(R.id.editTextTextPersonName);
        etAltura = findViewById(R.id.editTextTextPersonName2);


    }

    public void btnProcessar(View view){
        int peso = Integer.parseInt(String.valueOf(etPeso.getText()));
        float altura  = Float.parseFloat(String.valueOf(etAltura.getText()));


        AlertDialog.Builder builder = new AlertDialog.Builder(DataCheck.this);
        builder.setTitle("¡Resultados obtenidos!");
        builder.setMessage(processcheck(user, peso,altura));

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "UsersBodyEvolution", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        BaseDeDatos.execSQL("Update currentusers set estadosalud ="+user.getHealth()+" where id ="+user.getId());
        BaseDeDatos.close();
        Toast.makeText(this, "¡Estado de salud actualizado!, actual:"+user.getHealth(), Toast.LENGTH_SHORT).show();


        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            }
        });

        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(DataCheck.this, MainMenu.class);
                startActivity(intent);
            }
        });
        builder.show();


    }









}