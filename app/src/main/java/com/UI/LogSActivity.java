package com.UI;

import static com.UI.SigninActivity.user;
import static Controller.Administrator.createRoutine;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Model.User;

public class LogSActivity extends AppCompatActivity {

    private EditText etnombre, etcontra;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_sactivity);
        etnombre = findViewById(R.id.etnombre);
        etcontra = findViewById(R.id.etcontra);
    }


    public void LOGINF(View view) {

        String password = etcontra.getText().toString();
        String name = etnombre.getText().toString();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"UsersBodyEvolution",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

    if (!password.isEmpty() && !name.isEmpty()){
        Cursor fila = BaseDeDatos.rawQuery("select * from currentusers where nombre ="+"'"+name+"'"+"and password ="+"'"+password+"'",null);


        if (fila.moveToFirst()){


            int id = Integer.parseInt(fila.getString(0));
            String username =fila.getString(1);
            int edad =Integer.parseInt(fila.getString(3));
            int estadosalud = Integer.parseInt(fila.getString(4));
            user = new User(id, edad, estadosalud, 1, 1, 0, username);
            createRoutine(user);
            Intent intent4 = new Intent(this, MainMenu.class);
            startActivity(intent4);

            BaseDeDatos.close();
        }else {
            Toast.makeText(this, "No existe usuario", Toast.LENGTH_SHORT).show();
            BaseDeDatos.close();
        }
        }else {
        Toast.makeText(this, "Llene todos los datos", Toast.LENGTH_SHORT).show();
    }



    }

    public void SIGNINF(View view) {

        Intent intent3 = new Intent(this, SigninActivity.class);
        startActivity(intent3);


    }



}