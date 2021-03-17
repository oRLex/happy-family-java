package family;


import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public abstract class Pet  implements Serializable {
    private Species type = Species.UNKNOWN;
    private String nickname;
    private int age;
    private int trickLevel;
    private HashSet<String> habits;

    public Pet(){}

    public Pet(String nickname){
        this.nickname = nickname;
    }

    public Pet(String nickname, int age, int trickLevel, HashSet<String> habits) {
        this.nickname = nickname;
        this.age = age;
        this.trickLevel = trickLevel;
        this.habits = habits;
    }

    public String describeTricky() {
        if (trickLevel > 50) {
            return "очень хитрый";
        } else {
            return "почти не хитрый";
        }
    }

    public String eat() {
        return String.format("Я кушаю!");
    }

    public String respond() {
        return String.format("Привет, хозяин. Я - %s. Я соскучился!", nickname);
    }


    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getTrickLevel() {
        return trickLevel;
    }
    public void setTrickLevel(int trickLevel) {
        this.trickLevel = trickLevel;
    }
    public HashSet<String> getHabits() {
        return habits;
    }

    public void setHabits(HashSet<String> habits) {
        this.habits = habits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return age == pet.age && trickLevel == pet.trickLevel  && Objects.equals(nickname, pet.nickname) && Objects.equals(habits, pet.habits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, nickname, age, trickLevel, habits);
    }

    @Override
    public String toString() {
        return String.format("%s{nickname='%s', age=%s, tickLevel=%s, habits=%s}", type,  nickname, age, trickLevel, Arrays.toString(habits.toArray()));
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(toString());
        super.finalize();
    }

    public Species getType() {
        return type;
    }

    public void setType(Species type) {
        this.type = type;
    }

}
