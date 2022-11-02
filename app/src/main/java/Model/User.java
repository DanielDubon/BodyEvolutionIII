package Model;



public class User {


    private int age;
    private int health;
    private int sets;
    private int reps;
    private int indexofBS;
    private String name;

    public void bodyStatus(){

        indexofBS = health/age;
        System.out.println("mi nivel corporal es: "+indexofBS);
        setIndexofBS(indexofBS);

    }

    public User(int id,int age, int health, int sets, int reps, int indexofBS, String name){
    this.age = age;
    this.health = health;
    this.sets = sets;
    this.reps = reps;
    this.indexofBS = indexofBS;
    this.name = name;
    bodyStatus();

    }



    public void setName(String name) {
        this.name = name;
    }



    public void setHealth(int health) {
        this.health = health;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIndexofBS(int indexofBS){this.indexofBS = indexofBS;}
    public int getIndexofBS(){return indexofBS;}

    public int getAge() {
        return age;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }


    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }
    public void setReps(int reps) {
        this.reps = reps;
    }







}
