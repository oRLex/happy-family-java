package family.console;


import family.Family;
import family.Man;
import family.Woman;
import family.family.controller.FamilyController;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;

public class FamilyConsole {

    static List<String> menuList(){
        List<String> commands = Arrays.asList("1. Загрузить ранее сохраненные данные",
                "2. Экспорь в файл",
                "3. Отобразить весь список семей",
                "4. Отобразить список семей, где количество людей больше заданного",
                "5. Отобразить список семей, где количество людей меньше заданного",
                "6. Подсчитать количество семей, где количество членов равно",
                "7. Создать новую семью",
                "8. Удалить семью по индексу семьи в общем списке",
                "9. Редактировать семью по индексу семьи в общем списке",
                "10. Удалить всех детей старше возраста",
                "11. Выход");
        return commands;
    }
    static public Pattern pattern = Pattern.compile("[1-9]*");


    public FamilyConsole() throws IOException, ClassNotFoundException {
        System.out.println("*****Welcome to Family console app*****");
        progmam();
    }

    private final FamilyController controller = new FamilyController();
    static Scanner sc = new Scanner(System.in);
    static void displayMenu(){
        StringJoiner strb = new StringJoiner("\n");
        for (String s: menuList()){
            strb.add(s);
        }
        System.out.println(strb);
    }

    public void progmam() throws IOException, ClassNotFoundException {
        while (true){
            displayMenu();
            System.out.println("Choose the command:");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid command, shoot again:");
                sc.nextLine();
            }
            int command = sc.nextInt();

            switch (command){
                case 1: importData(); break;
                case 2: exportData(); break;
                case 3: displayFamilies(); break;
                case 4: displayFamiliesBiggerThan(); break;
                case 5: displayFamiliesLessThan(); break;
                case 6: countFamiliesWithMemberNumber(); break;
                case 7: createNewFamily(); break;
                case 8: deleteFamilyByIndex(); break;
                case 9: editFamily(); break;
                case 10: deleteAllChildrenOlderThan();
                case 11:System.exit(0); break;
                default: throw new IllegalArgumentException("Command not found");
            }
        }
    }

    private void exportData() throws IOException {
        controller.exportData();
    }

    public void displayFamilies(){
        controller.displayAllFamilies();
    }

    public void importData() throws IOException, ClassNotFoundException {
        controller.importData();
        System.out.println("FILLED");
    }

    public void displayFamiliesBiggerThan(){
        System.out.println("Type displayFamiliesBiggerThan");
        while (!sc.hasNextInt()){
            System.out.println("Invalid typed value");
            sc.nextLine();
        }
        int count = sc.nextInt();
        controller.getFamiliesBiggerThan(count);
    }

    public void displayFamiliesLessThan(){
        System.out.println("Type displayFamiliesLessThan");
        while (!sc.hasNextInt()){
            System.out.println("Invalid typed value");
            sc.nextLine();
        }
        int count = sc.nextInt();
        controller.getFamiliesBiggerThan(count);
    }

    public void countFamiliesWithMemberNumber(){
        System.out.println("Type countFamiliesWithMemberNumber");
        while (!sc.hasNextInt()){
            System.out.println("Invalid typed value");
            sc.nextLine();
        }
        int count = sc.nextInt();
        controller.countFamiliesWithMemberNumber(count);
    }


    public boolean isValidDate(String date){
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
            LocalDate.parse(date, dateTimeFormatter);
        }catch (DateTimeParseException exc) {
            return false;
        }
        return true;
    }

    public void createNewFamily(){
        System.out.println("имя матери");
        while (sc.hasNext(pattern)){
            System.out.println("Invalid typed value");
            sc.nextLine();
        }
        String woman_name = sc.next();
        System.out.println("фамилию матери");
        while (sc.hasNext(pattern)){
            System.out.println("Invalid typed value");
            sc.nextLine();
        }
        String woman_surname = sc.next();


        System.out.println("день месяц год рождения матери format(dd/MM/yyyy)");
        String motherBirthDate = sc.next();
        while (!isValidDate(motherBirthDate)){
            System.out.println("Invalid typed value");
            sc.nextLine();
        }
        String woman_bDate = motherBirthDate;


        System.out.println("iq матери");
        while (sc.hasNext(pattern)){
            System.out.println("Invalid typed value");
            sc.nextLine();
        }
        int woman_iq = sc.nextInt();


        System.out.println("имя отца");
        while (sc.hasNext(pattern)){
            System.out.println("Invalid typed value");
            sc.nextLine();
        }
        String man_name = sc.next();
        System.out.println("фамилию отца");
        while (sc.hasNext(pattern)){
            System.out.println("Invalid typed value");
            sc.nextLine();
        }
        String man_surname = sc.next();
        System.out.println("день месяц год рождения отца format(dd/MM/yyyy)");
        String fatherBrithDate = sc.next();
        while (!isValidDate(fatherBrithDate)){
            System.out.println("Invalid typed value");
            sc.nextLine();
        }
        String man_bDate = fatherBrithDate;

        System.out.println("iq отца");
        while (sc.hasNext(pattern)){
            System.out.println("Invalid typed value");
            sc.nextLine();
        }
        int man_iq = sc.nextInt();

        Woman mother = new Woman(woman_name, woman_surname, woman_bDate, woman_iq);
        Man father = new Man(man_name, man_surname, man_bDate, man_iq);
        controller.createNewFamily(mother,father);
    }

    public void deleteFamilyByIndex(){
        System.out.println("Type deleteFamilyByIndex");
        while (sc.hasNext(pattern)){
            System.out.println("Invalid typed value");
            sc.nextLine();
        }
        int id = sc.nextInt();
        controller.deleteFamilyByIndex(id);
    }

    public void editFamily() throws IOException, ClassNotFoundException {
        System.out.println("Type editFamily id");
        while (!sc.hasNextInt()){
            System.out.println("Invalid typed value");
            sc.nextLine();
        }
        int id = sc.nextInt();
        Family got = controller.getFamilyById(id);
        System.out.println("1. Родить ребенка");
        System.out.println("2. Усыновить ребенка");
        System.out.println("3. Вернуться в главное меню");
        int command = sc.nextInt();
        switch (command){
            case 1: bornChild(got); break;
            case 2: adoptChild(got); break;
            case 3: progmam(); break;
            default: System.exit(1);
        }
    }

    public void bornChild(Family f){
        System.out.println("Female name");
        while (!isValidString(sc.nextLine())){
            System.out.println("Invalid typed value");
            sc.nextLine();
        }
        String femaleName = sc.next();
        System.out.println("Male name");
        String maleName = sc.next();
        controller.bornChild(f,maleName,femaleName);
    }

    public static boolean isValidString(String s) {
        List<? extends Serializable> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9, 0, "-", "=", "+", "_", "`");
        return !list.contains(s);
    }

    public void adoptChild(Family f){
        System.out.println("Type adoptChild family id");
        while (!sc.hasNextInt()){
            System.out.println("Invalid typed value");
            sc.nextLine();
        }
        int id = sc.nextInt();

        System.out.println("имя ребенка");
        while (!isValidString(sc.nextLine())){
            System.out.println("Invalid typed value");
            sc.nextLine();
        }
        String child_name = sc.next();
        System.out.println("фамилию ребенка");
        while (!isValidString(sc.nextLine())){
            System.out.println("Invalid typed value");
            sc.nextLine();
        }
        String child_surname = sc.next();
        System.out.println(" день   месяц   год рождения ребенка format(dd/MM/yyyy)");
        while (!isValidString(sc.nextLine())){
            System.out.println("Invalid typed value");
            sc.nextLine();
        }
        String child_bDate = sc.next();
        System.out.println("iq ребенка");
        while (!sc.hasNextInt()){
            System.out.println("Invalid iq ребенка");
            sc.nextLine();
        }
        int child_iq = sc.nextInt();
        controller.adoptChild(f,new Man(child_name,child_surname,child_bDate,child_iq));
    }

    public void deleteAllChildrenOlderThan(){
        System.out.println("Type deleteAllChildrenOlderThan");
        while (!sc.hasNextInt()){
            System.out.println("Invalid typed value");
            sc.nextLine();
        }
        int year = sc.nextInt();
        controller.deleteAllChildrenOlderThan(year);
    }




}
