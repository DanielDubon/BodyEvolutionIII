

package com.UI;



import static com.UI.SigninActivity.user;
import static Controller.Administrator.createRoutine;
import static Controller.Administrator.processcheck;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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



    private EditText etnombre, etedad, etaltura, etpeso,etcontra;
    public static ArrayList<Long> users = new ArrayList<>();
    public static User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etnombre = findViewById(R.id.etnombre);
        etpeso  = findViewById(R.id.etpeso);
        etedad = findViewById(R.id.etedad);
        etaltura = findViewById(R.id.etaltura);
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

        checkpreferences();


    }

    private void checkpreferences() {

        SharedPreferences prefs = getSharedPreferences("Datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String name = prefs.getString("user", "");
        String password = prefs.getString("password", "");
        System.out.println("USUARIO RECUPERADO"+ name);
        System.out.println("CONTRA RECUPERADO"+ password);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "UsersBodyEvolution", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        if (!(name.equals("")) || !(password.equals(""))) {
            Cursor fila = BaseDeDatos.rawQuery("select * from currentusers where nombre =" + "'" + name + "'" + "and password =" + "'" + password + "'", null);
            System.out.println("INGRESAMOS A INICIAR SESION");

            if (fila.moveToFirst()) {

                System.out.println("INGRESAMOS A INICIAR SESION 2");
                int id = Integer.parseInt(fila.getString(0));
                String username = fila.getString(1);
                int edad = Integer.parseInt(fila.getString(3));
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
        }
    }

    public void SIGNIN(View view){

        if (!(etedad.getText().toString().equals(""))|| !(etpeso.getText().toString().equals("")) || !(etaltura.getText().toString().equals("")) || !(etcontra.getText().toString().equals(""))) {

            int edad = Integer.parseInt(etedad.getText().toString());
            float altura  = Float.parseFloat(String.valueOf(etaltura.getText()));
            int peso = Integer.parseInt(String.valueOf(etpeso.getText()));
            String password = etcontra.getText().toString();
            String name = etnombre.getText().toString();

            SharedPreferences prefs = getSharedPreferences("Datos", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("user",name);
            editor.putString("password",password);
            editor.commit();

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"UsersBodyEvolution",null,1);
            SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

            ContentValues registro = new ContentValues();

            int currentid = generarIDPlayer(users);
            registro.put("id",currentid);
            registro.put("nombre",name);
            registro.put("edad",edad);
            registro.put("password",password);
            user = new User(currentid,edad, 0, 1, 1, 0, name);
            processcheck(user,peso,altura);
            System.out.println("SALUD "+user.getHealth());
            registro.put("estadosalud",user.getHealth());
            prefs.edit().putString("username", user.getName()).apply();
            prefs.edit().putString("password", password).apply();
            BaseDeDatos.insert("currentusers",null,registro);
            BaseDeDatos.close();
            etedad.setText("");
            etpeso.setText("");
            etaltura.setText("");
            etnombre.setText("");
            etcontra.setText("");

            Toast toast = Toast.makeText(this,"Registro terminado! ", Toast.LENGTH_SHORT);
            toast.show();



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