package family.family.dao;

import family.Family;

import java.io.IOException;
import java.util.List;


public interface FamilyDao<A> {
    List<Family> getAllFamilies();
    Family getFamilyByIndex(int index);
    boolean deleteFamily(Family f);
    boolean deleteFamily(int index);
    void saveFamily(Family f);
    void exportData(List<Family> families) throws IOException;
    void importData() throws IOException, ClassNotFoundException;

}
