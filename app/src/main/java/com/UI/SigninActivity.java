

package com.UI;



import static Controller.Administrator.createRoutine;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import Model.User;

public class SigninActivity extends AppCompatActivity {

    private EditText etnombre, etedad, etestadosalud, etcontra;
    public static ArrayList<Long> users = new ArrayList<>();
    public static User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etnombre = findViewById(R.id.etnombre);
        etedad = findViewById(R.id.etedad);
        etestadosalud = findViewById(R.id.etestadosalud);
        etcontra = findViewById(R.id.etcontra);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"UsersBodyEvolution",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        Cursor cursor = BaseDeDatos.rawQuery("select * from currentusers",null);


        while (cursor.moveToNext()){
            long usuario = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
            users.add(usuario);



        }
        for (Long user: users){
            System.out.println("usuario id--"+user);
        }
        cursor.close();
        BaseDeDatos.close();




    }



    public void SIGNIN(View view){

        if (!(etedad.getText().toString().equals(""))|| !(etestadosalud.getText().toString().equals("")) || !(etestadosalud.getText().toString().equals("")) || !(etcontra.getText().toString().equals(""))) {

            int edad = Integer.parseInt(etedad.getText().toString());
            int health = Integer.parseInt(etestadosalud.getText().toString());
            String password = etcontra.getText().toString();
            String name = etnombre.getText().toString();

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"UsersBodyEvolution",null,1);
            SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

            ContentValues registro = new ContentValues();

            int currentid = generarIDPlayer(users);
            registro.put("id",currentid);
            registro.put("nombre",name);
            registro.put("edad",edad);
            registro.put("password",password);
            registro.put("estadosalud",health);

            BaseDeDatos.insert("currentusers",null,registro);
            BaseDeDatos.close();
            etedad.setText("");
            etestadosalud.setText("");
            etnombre.setText("");
            etcontra.setText("");

            Toast toast = Toast.makeText(this,"Registro terminado! ", Toast.LENGTH_SHORT);
            toast.show();


            user = new User(currentid,edad, health, 1, 1, 0, name);
            createRoutine(user);
            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
        }else{
            Toast toast = Toast.makeText(this,"Complete sus datos", Toast.LENGTH_SHORT);
                toast.show();
        }

    }

    //Metodo para Login de usuario

    public void LOGIN(View view) {

        Intent intent2 = new Intent(this, LogSActivity.class);
        startActivity(intent2);

    }


    private int generarIDPlayer(ArrayList<Long> users) {
        int IDUSU = 1;

        for (Long user: users) {
            IDUSU = IDUSU + 1;
        }
        return IDUSU;
    }




}