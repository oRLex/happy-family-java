package family;

import java.io.Serializable;
import java.util.*;

public class Family implements Serializable {
    public static final int maxCountMember = 4;
    private Human mother;
    private Human father;
    private ArrayList<Human> children;
    private HashSet<Pet> pet;

    public Family(Woman mother, Man father) {
        this.mother = mother;
        this.father = father;
        this.children = new ArrayList<Human>(0);
        this.pet = new HashSet<Pet>();
        mother.setFamily(this);
        father.setFamily(this);
    }


    public Family(Woman mother, Man father, ArrayList<Human> children, HashSet<Pet> pet) {
        this.mother = mother;
        this.father = father;
        this.children = children;
        this.pet = pet;
        for (Human p:children) {
            p.setFamily(this);
        }
        mother.setFamily(this);
        father.setFamily(this);
    }

    public Family(Woman mother, Man father, HashSet<Pet> pet) {
        this.mother = mother;
        this.father = father;
        this.children = new ArrayList<Human>(0);
        this.pet = pet;
        mother.setFamily(this);
        father.setFamily(this);
    }

    public Family(Human mother, Human father) {
        this.mother = mother;
        this.father = father;
        mother.setFamily(this);
        father.setFamily(this);
    }

    public String prettyFormat(){
        StringJoiner strb = new StringJoiner("\n");
        strb.add("family ");
        if (children.size() == 0){
            strb.add(String.format(" %15s: %s", "mother", mother.prettyFormat()));
            strb.add(String.format(" %15s: %s", "father", father.prettyFormat()));
            strb.add(String.format(" %15s: %s", "pet", pet.toString()));
            return strb.toString();
        } else {
            strb.add(String.format(" %15s: %s", "mother", mother.prettyFormat()));
            strb.add(String.format(" %15s: %s", "father", father.prettyFormat()));
            strb.add(String.format(" %15s", "children"));
            for (Human child: children){
                if (child instanceof Man){
                    strb.add(String.format(" %20s: %s", "boy", child.prettyFormat()));
                } else {
                    strb.add(String.format(" %20s: %s", "girl", child.prettyFormat()));
                }
            }
            strb.add(String.format(" %15s:", "pet", pet.toString()));

            return strb.toString();
        }
    }

    public void addChild(Human c){
        if (c != null){
            c.setFamily(this);
            for (Human x : children) {
                if (x.equals(c)) throw new IllegalArgumentException(); break;
            }
            children.add(c);
        }
    }

    public Human getChild(int index) {
        return children.get(index);
    }

    public ArrayList<Human> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Human> children) {
        this.children = children;
    }

    public void deleteChild(int index){
        if (index > children.size() -1) {
            throw new IndexOutOfBoundsException();
        } else {
            children.get(index).setFamily(null);
            children.remove(index);
        }
    }
    public void deleteChild(Human c){
        if (c != null &&  children.contains(c)){
           children.remove(c);
           c.setFamily(null);
        }
    }

    public int count(){
        children.trimToSize();
        return 2 + children.size();
    }

    public HashSet<Pet> getPet() {
        return pet;
    }

    public void setPet(HashSet<Pet> pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Family)) return false;
        Family family = (Family) o;
        return mother.equals(family.mother) && father.equals(family.father) && Objects.equals(children, family.children);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash( mother, father);
        result = 31 * result + Arrays.hashCode(children.toArray());
        return result;
    }

    public Human getMother() {
        return mother;
    }

    @Override
    public String toString() {
        if (children.size() == 0){
            return String.format("mother=%s, \n father=%s,\n", mother, father);
        } else {
        return String.format("mother=%s, \n father=%s, \n children=%s,\n", mother, father, Arrays.toString(children.toArray()));
        }
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(toString());
        super.finalize();
    }
}
