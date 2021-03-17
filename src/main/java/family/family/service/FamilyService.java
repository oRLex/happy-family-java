package family.family.service;


import family.*;
import family.family.collection.CollectionFamilyDao;
import family.family.dao.FamilyDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FamilyService {
    private FamilyDao<Family> db  = new CollectionFamilyDao<>();

    public void exportData() throws IOException {
        db.exportData(db.getAllFamilies());
    }

    public void importData() throws IOException, ClassNotFoundException{
      db.importData();
    }


    public FamilyDao<Family> getDb() {
        return db;
    }

    public List<Family> geAllFamilies(){
        return db.getAllFamilies();
    }

    public void displayAllFamilies(){
        try {
            List<Family> allFamilies = db.getAllFamilies();
            allFamilies.forEach(x-> System.out.println(x.prettyFormat()));
        } catch (NullPointerException e){
            System.out.println("displayAllFamilies: The db is empty");
        }
    }

    public void displayAllFamilies(List<Family> families) {
        try {
            if(families.size() == 0) {
                throw new NullPointerException("I cannot find the data according to the conditions");
            }
            families.forEach(x-> System.out.println(x.prettyFormat()));
        } catch (NullPointerException e){
            System.out.println(e);
        }
    }

    public void getFamiliesBiggerThan(int c){
        try {
            List<Family> collect = db.getAllFamilies().stream()
                    .filter(x -> x.count() > c)
                    .collect(Collectors.toList());
            displayAllFamilies(collect);
        } catch (NullPointerException e) {
            System.out.println("cannot find the data according to the conditions");
        }
    }

    public void getFamiliesLessThan(int c){
        try {
            List<Family> collect = db.getAllFamilies().stream()
                    .filter(x -> x.count() < c)
                    .collect(Collectors.toList());
            displayAllFamilies(collect);
        } catch (NullPointerException e){
            System.out.println("cannot find the data according to the conditions");
        }
    }

    public void countFamiliesWithMemberNumber(int c){
        try {
            long count = db.getAllFamilies().stream().filter(x -> x.count() == c).count();
            System.out.printf("countFamiliesWithMemberNumber: %d \n",count);
        } catch (NullPointerException e){
            System.out.println("countFamiliesWithMemberNumber:No families in db!!!!!");
        }
    }

    public void createNewFamily(Woman mother, Man father) {
        try {
            if (mother == null || father == null) throw new NullPointerException("mother or father is null");
            Family family = new Family(mother, father);
            db.saveFamily(family);
        } catch (NullPointerException e){
            System.out.println("cannot create a family");
        }
    }

    public void deleteFamilyByIndex(int index){
        try {
            if (index > db.getAllFamilies().size()) throw new NumberFormatException("index is bigger than db size");
            db.deleteFamily(index);
        } catch (NumberFormatException e){
            System.out.println(e);
        }
    }

    public void bornChild(Family f, String maleName, String femaleName){
        try{
            if (db.getAllFamilies().contains(f)){

                int randomGender = (int) (Math.random()*1);
                Human ch;
                if (randomGender == 1){
                    ch = new Woman(femaleName, f.getMother().getSurname());
                } else {
                    ch = new Man(maleName, f.getMother().getSurname());
                }
                f.addChild(ch);
                int i = db.getAllFamilies().indexOf(f);
                db.getAllFamilies().set(i,f);
            }
        } catch (NullPointerException e){
            System.out.println("Can not born child");
        }
    }

    public void adoptChild(Family f, Human child){
        try {
            if (db.getAllFamilies().contains(f)){
                f.addChild(child);
                int i = db.getAllFamilies().indexOf(f);
                db.getAllFamilies().set(i,f);
            }
        } catch (NullPointerException e){
            System.out.println("can not adoptchild");
        }
    }

    public void deleteAllChildrenOlderThan(int num){
        db.getAllFamilies().stream().forEach(x->{
            ArrayList<Human> collect =(ArrayList<Human>) x.getChildren().stream().filter(y -> y.getFullYear() > num).collect(Collectors.toList());
            x.setChildren(collect);
        });
    }

    public void count(){
        System.out.printf("Count %d",db.getAllFamilies().size());
    }

    public Family getFamilyById(int id){
        try {
            if (id > db.getAllFamilies().size()){ throw new NumberFormatException("index is bigger than db size");}
            return db.getFamilyByIndex(id);
        } catch (NullPointerException e){
            System.out.println(e);
            return null;
        }
    }

    public Set<Pet> getPets(int index){
        try {
            if (index > db.getAllFamilies().size()) {
                throw new NumberFormatException("index is bigger than db size");
            }
            Family f = getFamilyById(index);
            return f.getPet();
        } catch (NullPointerException e){
            System.out.println(e);
            return null;
        }
    }

    public void addPet(int index, Pet p){
        Family f = getFamilyById(index);
        HashSet<Pet> pets = f.getPet();
        pets.add(p);
        f.setPet(pets);
        int i = db.getAllFamilies().indexOf(f);
        db.getAllFamilies().set(i,f);
    }
}
