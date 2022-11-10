package Controller;

import android.database.sqlite.SQLiteDatabase;

import com.UI.*;

import Model.User;


public class Administrator {

    public static void createRoutine(User user){

       int indexofBS =  user.getIndexofBS();

        if (indexofBS>=6){
            user.setSets(4);
            user.setReps(12);
        }else if(indexofBS >= 4){
            user.setSets(3);
            user.setReps(10);

        }else if(indexofBS >= 2){
            user.setSets(2);
            user.setReps(8);
        }else if(indexofBS >= 1){
            user.setSets(1);
            user.setReps(6);
        }else{
            user.setSets(1);
            user.setReps(4);
        }


    }

    public static String processcheck(User user,int peso, float altura){
        float imc = peso/(altura*altura);
        System.out.println("TU IMC ES: "+imc);
        int indexH;
        String process="";
        if (imc <= 18.5){
            indexH = 1;
            process = "Tu indice de masa corporal es: "+imc+" esto quiere decir que tienes un indice inferior a lo saludable, intenta comer mas y realizar menos cardio";

        }else if(imc <= 24.9){
            indexH = 2;
            process = "Tu indice de masa corporal es: "+imc+" esto quiere decir que tienes un cuerpo saludable, sigue asi ahora a incrementar masa muscular, come mas proteina y haz ejercicio";
        }else if (imc <= 29.9){
            indexH = 3;
            process = "Tu indice de masa corporal es: "+imc+" esto quiere decir que tienes un poco de obesidad, reduce la cantidad de comida que comes e intenta comer mas saludable, complementalo con ejercicio";
        }else{
            indexH = 4;
            process = "Tu indice de masa corporal es: "+imc+" esto quiere decir que tienes obesidad, reduce la cantidad de comida que comes y come mas saludable, complementalo con ejercicio, consejo: inicia levemente";
        }

        if (indexH == 1){
            user.setHealth(70);
        }else if(indexH == 2){
            user.setHealth(100);
        }else if (indexH == 3){
            user.setHealth(60);
        }else if (indexH == 4){
            user.setHealth(50);
        }




        return process;
    }



}
