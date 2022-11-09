package Model;

import java.util.Random;

public class Workout {
    private int sets;
    private String excersise;




    public String chooseExercise(){

        Random random = new Random();
        switch ((int)(random.nextInt(6))){

            case 1:
                excersise = "Despechada";
                break;
            case 2:
                excersise = "Sentadilla";
                break;
            case 3:
                excersise = "Abdominales";
                break;
            case 4:
                excersise = "Desplantes";
                break;
            case 5:
                excersise = "Burpees";
                break;
            case 6:
                excersise = "Payasitos";
                break;
        }

        return excersise;
    }








    public String getExcersise(){
        return chooseExercise();
    }
    public void setExcersise(String excersise){
        this.excersise = excersise;
    }


    public int getSets() {
        return sets;
    }


    public void setSets(int sets) {
        this.sets = sets;
    }


}
