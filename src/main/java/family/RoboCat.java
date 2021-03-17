package family;

import java.util.HashSet;

public class RoboCat extends Pet implements IAnimalActions {
    private final Species type = Species.ROBO_CAT;
    public RoboCat(String nickname){
        super(nickname);
        this.setType(type);
    }
    public RoboCat(String nickname, int age, int trickLevel, HashSet<String> habits) {
        super(nickname, age, trickLevel, habits);
        this.setType(type);
    }


    @Override
    public String foul() {
        return String.format("Нужно хорошо замести следы...");
    }
}
