package family;

import java.util.HashSet;

public class Fish extends Pet {
    private final Species type = Species.FISH;

    public Fish(String nickname){
        super(nickname);
        this.setType(type);
    }

    public Fish(String nickname, int age, int trickLevel, HashSet<String> habits) {
        super(nickname, age, trickLevel, habits);
        this.setType(type);
    }

}
