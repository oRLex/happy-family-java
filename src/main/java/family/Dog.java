package family;

import java.util.HashSet;

public class Dog extends Pet {
    final Species type = Species.DOG;
    public Dog(String nickname){
        super(nickname);
        this.setType(type);
    }

    public Dog(String nickname, int age, int trickLevel, HashSet<String> habits) {
        super(nickname, age, trickLevel, habits);
        this.setType(type);
    }


}
