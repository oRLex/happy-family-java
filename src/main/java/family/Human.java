package family;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public abstract class Human  implements Serializable {
    public static ZoneId zoneId = ZoneId.systemDefault();
    private String name;
    private String surname;
    private long birthDate;
    private int iq;
    private Family family;
    private HashMap<String, String> schedule;

    public Human(){};

    public Human(String name, String surname, String birthDate){
        this.name = name;
        this.surname = surname;
        if (birthDate != null) {
            this.birthDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/M/yyyy")).toEpochDay();
        } else {
            this.birthDate = LocalDate.now().toEpochDay();
        }
    }

    public Human(String name, String surname, String birthDate, int iq) {
        this(name,surname,birthDate);
        this.iq = iq;
    }



    public Human(String name, String surname, String birthDate, int iq, HashMap<String, String> schedule) {
        this(name,surname,birthDate,iq);
        this.schedule = schedule;
    }

    public Human(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.birthDate = LocalDate.now().toEpochDay();
    }


    private String formatSchedule(){
        StringBuilder sb = new StringBuilder("");

        if (schedule.size() != 0){
                 sb.append("[");
                for (String key : schedule.keySet()){
                    sb.append("[");
                    sb.append(key + " ");
                    sb.append(schedule.get(key));
                    sb.append("]");
                }
                sb.append("]");
             }
            return sb.toString();
    }

    public abstract String greetPet();

    public String describePet(){
        StringBuilder sb = new StringBuilder("");

        HashSet<Pet> pets = getFamily().getPet();
        Iterator itr = pets.iterator();
        while (itr.hasNext()){
            Pet p = (Pet) itr.next();
            sb.append("У меня есть ");
            sb.append(p.getNickname());
            sb.append(" ему ");
            sb.append(p.getAge());
            sb.append(" лет, он ");
            sb.append(p.describeTricky());
        }
        return sb.toString();
    }
    public String formatPets(HashSet<Pet> pet){
        StringBuilder sb = new StringBuilder("");
        Iterator itr = pet.iterator();
        while (itr.hasNext()){
            Pet p = (Pet) itr.next();
            sb.append("[");
            sb.append(p.toString());
            sb.append("]");
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getBirthDate() {
        return birthDate;
    }
    public int getFullYear(){
        LocalDate bday = LocalDate.ofEpochDay(getBirthDate());
        Period period = Period.between(bday, LocalDate.now());
        return period.getYears();
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/M/yyyy")).toEpochDay();
    }

    public int getIq() {
        return iq;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }


    public HashMap<String, String> getSchedule() {
        return schedule;
    }

    public void setSchedule(HashMap<String, String> schedule) {
        this.schedule = schedule;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public String describeAge(){
        LocalDate bday = LocalDate.ofEpochDay(birthDate);
        Period period = Period.between(bday, LocalDate.now());
        return String.format("Years %s, month %s, days: %s", period.getYears(), period.getMonths(), period.getDays());

    }


    public String prettyFormat(){
        if (schedule == null || iq == 0 || getFamily().getPet() == null) {
            return String.format("{name='%s', surname='%s', birthDate=%s, iq=%s,}", name, surname, printBirthday(), iq);
        } else {
            return String.format("{name='%s', surname='%s', birthDate=%s, iq=%d,schedule=%s}", name, surname, printBirthday(), iq, formatSchedule());
        }
    }

    @Override
    public String toString() {
        if (schedule == null || iq == 0 || getFamily().getPet() == null) {
            return String.format("Human{name='%s', surname='%s', year=%s}", name, surname, printBirthday());
        } else {
            return String.format("Human{name='%s', surname='%s', year=%s, iq=%d, pet=%s schedule=%s}", name, surname, printBirthday(), iq, formatPets(family.getPet()), formatSchedule());
        }
    }

    private String printBirthday() {
        LocalDate bday = LocalDate.ofEpochDay(birthDate);
        return bday.format(DateTimeFormatter.ofPattern("dd/M/yyyy"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human)) return false;
        Human human = (Human) o;
        return birthDate == human.birthDate && iq == human.iq && name.equals(human.name) && surname.equals(human.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, birthDate, iq, family);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(toString());
        super.finalize();
    }
}
