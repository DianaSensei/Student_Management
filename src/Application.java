import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Application {
    Boolean isLogin;
    private Manager_System manager_system ;
    private Application(){
        manager_system = new Manager_System();
    }
    private void Menu_Start() {
        ConsoleColors.clearConsole();
        System.out.println(ConsoleColors.BLUE_BOLD + "Student Manager");
        System.out.println(ConsoleColors.WHITE + "1. Load Student List from .CSV file");
        System.out.println(ConsoleColors.WHITE + "2. Save Student List to .CSV file");
        System.out.println(ConsoleColors.WHITE + "3. View Student List");
        System.out.println(ConsoleColors.WHITE + "4. Add new Student to List");
        System.out.println(ConsoleColors.WHITE + "5. Delete Student from List");
        System.out.println(ConsoleColors.WHITE + "6. Import/Export Student List from/to .CSV file");
        System.out.println(ConsoleColors.WHITE_BOLD+"8. Quit");
        int selected = getIndexCommand(1, 6);
        switch (selected) {
                case 1: {
                    System.out.println("Select " + selected);
                    break;
                }
                case 2: {
                    System.out.println("Select " + selected);
                    break;
                }
                case 3: {
                    View_Menu();
                    break;
                }
                case 4: {
                    System.out.println("Select " + selected);
                    break;
                }
                case 5: {
                    System.out.println("Select " + selected);
                    break;
                }
                case 6: {
                    ImportExport_Menu();
                    break;
                }
        }
    }
    private int getIndexCommand(int min, int max){
        int selected = 0;boolean flag = false;
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        do{
            flag = false;
            System.out.println("Enter number of command: ");
            try {
                String in = scanner.next();
                int temp = Integer.parseInt(in);
                selected = temp;
            }catch (Exception e){
                flag = true;
                System.out.println("Invalid command index!!");
            }
            if(min <=selected || selected <= max) break;
        }while (flag);
        return selected;
    }

    private void View_Menu(){
        ConsoleColors.clearConsole();
        System.out.println(ConsoleColors.BLUE_BOLD + "Student Manager");
        System.out.println(ConsoleColors.YELLOW_BOLD+"VIEW");
        System.out.println(ConsoleColors.WHITE+"1. View Student List with ID");
        System.out.println(ConsoleColors.WHITE+"2. View Student List with GPA");
        System.out.println(ConsoleColors.WHITE_BOLD+"3. Back");
        int selected = getIndexCommand(1,3);
    }
    private void ImportExport_Menu(){
        ConsoleColors.clearConsole();
        System.out.println(ConsoleColors.BLUE_BOLD + "Student Manager");
        System.out.println(ConsoleColors.YELLOW_BOLD+"IMPORT/EXPORT");
        System.out.println(ConsoleColors.WHITE+"1. Import Student List from .CSV file");
        System.out.println(ConsoleColors.WHITE+"2. Export Student List to .CSV file");
        System.out.println(ConsoleColors.WHITE_BOLD+"3. Back");
        int selected = getIndexCommand(1,3);
    }
    public static void main(String[] args) {
        Application app = new Application();
        app.Menu_Start();
    }

    private void Add(Scanner scanner){
        System.out.println("***** ADD New Student *****\n");
        Student student = new Student();
        System.out.print("ID: ");
        student.setID(scanner.nextLine());
        System.out.print("Name: ");
        student.setName(scanner.nextLine());
        System.out.print("Address: ");
        student.setAddress(scanner.nextLine());
        System.out.print("GPA: ");
        student.setGPA(scanner.nextDouble());
        System.out.print("Note: ");
        student.setNotes(scanner.nextLine());

        if(manager_system.AddStudent(student)){
            System.out.println("Add new Student Successfully");
        }else {
            System.out.println("Fail to Add Student!!");
        }
    }
    private void Delete(Scanner scanner){
        System.out.println("***** Remove New Student *****\n");
        System.out.println("Enter Student ID: ");
        if(manager_system.RemoveStudent_by_ID(scanner.nextLine())) {
            System.out.println("Remove Successful!");
        }else {
            System.out.println("Fail to Remove Student!");
        }
    }
    private void Import(){
// TO DO
    }
    private void Export(){
// TO DO
    }
    private void Save_to_File(){
// TO DO
    }
    private void View(int type,boolean order){
        if(type == 1 ) manager_system.View_by_GPA(order,System.out);
        if(type == 0 ) manager_system.View_by_ID(order,System.out);
    }
}
