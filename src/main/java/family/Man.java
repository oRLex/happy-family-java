package family;

import java.util.HashMap;

public final class Man extends Human {



    public Man(String name, String surname, String birthDate){
        super(name, surname, birthDate);
    }

    public Man(String name, String surname, String birthDate, int iq, HashMap<String, String> schedule){
        super(name, surname, birthDate, iq, schedule);
    }
    public Man(String name, String surname){
        super(name, surname);
    }

    public Man(String name, String surname, String birthDate, int iq) {
        super(name, surname, birthDate, iq);
    }

    @Override
    public String greetPet() {
        return String.format("%s", getFamily().getPet());
    }

    public void repairCar(){
        System.out.printf("%s is starting to repair the car", super.getName());
    }

}
