package family;

import java.util.HashSet;

public class DomesticCat extends Pet {
    final Species type = Species.DOMESTIC_CAT;
    public DomesticCat( String nickname){
        super(nickname);
    }

    public DomesticCat(String nickname, int age, int trickLevel, HashSet<String> habits) {
        super(nickname, age, trickLevel, habits);
    }
}
