package family;

import java.util.HashMap;

public class Woman extends Human {

    public Woman(String name, String surname, String birthDate){
        super(name, surname, birthDate);
    }
    public Woman(String name, String surname){
        super(name, surname);
    }

    public Woman(String name, String surname, String birthDate, int iq, HashMap<String, String> schedule){
        super(name, surname, birthDate, iq, schedule);
    }
    public Woman(String name, String surname, String birthDate, int iq){
        super(name, surname, birthDate, iq);

    }


    @Override
    public String greetPet() {
        return String.format("%s",getFamily().getPet());
    }

    public void makeUp(){
        System.out.printf("%s starts to makeup", super.getName());
    }


}
