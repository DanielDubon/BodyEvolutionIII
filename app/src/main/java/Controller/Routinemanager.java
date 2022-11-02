package Controller;

import com.UI.*;

import Model.User;


public class Routinemanager {

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



}
