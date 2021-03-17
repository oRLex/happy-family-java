package family.family.service;

import family.*;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class FamilyServiceTest {
    public HashMap<String, String> schedule(){
        HashMap<String, String> schedule = new HashMap<>();
        schedule.put(DayOfWeek.SUNDAY.name(),"do home work");
        return schedule;
    }

    public HashSet<String> generatePet(){
        HashSet<String> habits1 = new HashSet<>();
        habits1.add("find parents");
        habits1.add("forget");
        return habits1;
    }

    public Human generateChild(){
        return new Man("Evgeniy", "Ponasenkov", "29/10/2000", 200);
    }


    public FamilyService generateFamilyDB(){
        FamilyService db = new FamilyService();

        db.createNewFamily(new Woman("Nelson", "Barajas", "29/10/2000", 90, schedule()), new Man("Ara ", "Barajas", "29/10/2000", 90,schedule()));
        return db;
    }

    @Test
    void geAllFamilies() {
        FamilyService db = generateFamilyDB();
        assertEquals(db.geAllFamilies().size(),1);

    }


    @Test
    void createNewFamily() {
        FamilyService db = generateFamilyDB();
        db.createNewFamily(new Woman("Caroline", "Hill", "29/10/2000", 90, schedule()), new Man("Yoshinobu", "Hill", "29/10/2000", 90,schedule()));
        assertEquals(db.geAllFamilies().size(),2);
    }

    @Test
    void deleteFamilyByIndex() {
        FamilyService db = generateFamilyDB();
        db.deleteFamilyByIndex(0);
        assertEquals(db.getFamilyById(0),null);
    }

    @Test
    void bornChild() {
        FamilyService db = generateFamilyDB();
        db.bornChild(db.getFamilyById(0),"Boris", "Polya");
    }

    @Test
    void adoptChild() {
        FamilyService db = generateFamilyDB();
        db.adoptChild(db.getFamilyById(0),generateChild());
    }

}