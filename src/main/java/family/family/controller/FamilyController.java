package family.family.controller;

import family.*;
import family.exceptions.FamilyOverflowException;
import family.family.service.FamilyService;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class FamilyController {
    private FamilyService familyService = new FamilyService();

    public void importData() throws IOException, ClassNotFoundException {
        familyService.importData();
    }
    public void exportData() throws IOException {
        familyService.exportData();
    }

    public List<Family> geAllFamilies(){
        return familyService.geAllFamilies();
    }

    public void displayAllFamilies(){
        familyService.displayAllFamilies();
    }

   public void getFamiliesBiggerThan(int c){
       System.out.println("Families Bigger Than");
       familyService.getFamiliesBiggerThan(c);
    }

   public void getFamiliesLessThan(int c){
       System.out.println("Families less Than");
      familyService.getFamiliesLessThan(c);
    }

   public void countFamiliesWithMemberNumber(int c){
       familyService.countFamiliesWithMemberNumber(c);
    }


    public void deleteFamilyByIndex(int index){
       familyService.deleteFamilyByIndex(index);
    }

    public void bornChild(Family f, String maleName, String femaleName){
            if (f.count() >= Family.maxCountMember){
                throw new FamilyOverflowException("Can't born a child");
            } else {
                familyService.bornChild(f,maleName, femaleName);
            }
    }

    public void adoptChild(Family f, Human child){
            if (f.count() >= Family.maxCountMember){
                throw new FamilyOverflowException("Can't adopt a child");
            } else {
                familyService.adoptChild(f,child);
            }
        }

    public void deleteAllChildrenOlderThan(int num){
        familyService.deleteAllChildrenOlderThan(num);
    }

    public void count(){
        familyService.count();
    }

    public Family getFamilyById(int id){
        return familyService.getFamilyById(id);
    }

    public Set<Pet> getPets(int index){
        return familyService.getPets(index);
    }

    public void addPet(int index, Pet p){
        familyService.addPet(index,p);
    }

    public void createNewFamily(Woman mother, Man father){
        familyService.createNewFamily(mother, father);
    }

}
