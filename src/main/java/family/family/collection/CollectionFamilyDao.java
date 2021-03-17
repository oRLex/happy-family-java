package family.family.collection;

import family.Family;
import family.family.dao.FamilyDao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CollectionFamilyDao<A> implements FamilyDao<A> {
    private  List<Family> db = new ArrayList<>();



    @Override
    public List<Family> getAllFamilies() {
        if (!(db.isEmpty())){
            return db;
        } else {
            return null;
        }
    }

    @Override
    public Family getFamilyByIndex(int index) {
        if (index > db.size() - 1){
            return null;
        } else {
            return  db.get(index);
        }
    }

    @Override
    public boolean deleteFamily(Family f) {
        if (!db.contains(f)){
            return false;
        } else {
            db.remove(f);
            return true;
        }
    }

    @Override
    public boolean deleteFamily(int index) {
        if (index > db.size() - 1){
            return false;
        } else {
            db.remove(index);
            return true;
        }
    }

    @Override
    public void saveFamily(Family f) {
        if (db.contains(f)){
            int i = db.indexOf(f);
            db.set(i,f);
        } else {
            db.add(f);
        }
    }

    @Override
    public void exportData(List<Family> families) throws IOException {
        File file = new File("family_db.bin");
        FileOutputStream fos = new FileOutputStream(file);
        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(families);
        }
    }

    @Override
    public void importData() throws IOException, ClassNotFoundException {
        File file = new File("family_db.bin");
        FileInputStream fis = new FileInputStream(file);
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            List<Family> families= (List<Family>) ois.readObject();
            db.addAll(families);
        }

    }
}
