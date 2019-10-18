import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.server.ExportException;
import java.util.Collections;
import java.util.Scanner;

public class Application {
    Boolean isLogin;
    private Manager_System manager_system ;
    private Application(){
        manager_system = new Manager_System();
    }
    private int getIndexCommand(int min, int max){
        int selected = 0;boolean flag = false;
        Scanner scanner = new Scanner(System.in);
        while(!flag){
            System.out.println("Enter number of command: ");
            try {
                int temp = scanner.nextInt();
                selected = temp;
                flag = true;
            }catch (Exception e){
                System.out.println("Invalid command index!!");
            }
            if(min <=selected || selected <= max) break;
        };
        return selected;
    }
    private int LoadSave_Menu() throws Exception{
        ConsoleColors.clearConsole();
        System.out.println(ConsoleColors.BLUE_BOLD + "STUDENT MANAGER");
        System.out.println(ConsoleColors.YELLOW_BOLD+"Load/Save");
        System.out.println(ConsoleColors.WHITE+"1. Load Student List from file");
        System.out.println(ConsoleColors.WHITE+"2. Save Student List to file");
        System.out.println(ConsoleColors.WHITE_BOLD+"3. Back");
        int selected = getIndexCommand(1,3);
        switch (selected){
            case 3:{
                return -1;
            }
            case 1:{

                break;
            }
            case 2:{

                break;
            }
        }
        return 1;
    }
    private int Menu_Start() throws Exception {
        ConsoleColors.clearConsole();
        System.out.println(ConsoleColors.BLUE_BOLD + "STUDENT MANAGER");
        System.out.println(ConsoleColors.WHITE + "1. Load/Save Student List from/to file");
        System.out.println(ConsoleColors.WHITE + "2. View Student List");
        System.out.println(ConsoleColors.WHITE + "3. Add new Student to List");
        System.out.println(ConsoleColors.WHITE + "4. Delete Student from List");
        System.out.println(ConsoleColors.WHITE + "5. Import/Export Student List from/to .CSV file");
        System.out.println(ConsoleColors.WHITE_BOLD+"6. Quit");
        int selected = getIndexCommand(1, 6);
        switch (selected) {
            case 1: {
                if(LoadSave_Menu() == -1 ) return 1;
                else LoadSave_Menu();
                break;
            }
            case 2: {
                if(View_Menu() == -1) return 1;
                else View_Menu();
                break;
            }
            case 3: {
                Add();
                break;
            }
            case 4: {
                Delete();
                break;
            }
            case 5: {
                if(ImportExport_Menu() == -1) return 1;
                else ImportExport_Menu();
                break;
            }
            case 6: {
                return -1;
            }
        }
        return 1;
    }
    private int View_Menu(){
        ConsoleColors.clearConsole();
        System.out.println(ConsoleColors.BLUE_BOLD + "STUDENT MANAGER");
        System.out.println(ConsoleColors.YELLOW_BOLD+"VIEW");
        System.out.println(ConsoleColors.WHITE+"1. View Student List with ID Ascending");
        System.out.println(ConsoleColors.WHITE+"2. View Student List with ID Descending");
        System.out.println(ConsoleColors.WHITE+"3. View Student List with GPA Ascending");
        System.out.println(ConsoleColors.WHITE+"4. View Student List with GPA Descending");
        System.out.println(ConsoleColors.WHITE_BOLD+"5. Back");
        int selected = getIndexCommand(1,3);
        switch (selected){
            case 1:{
                manager_system.View_by_ID(true,System.out);break;
            }
            case 2:{
                manager_system.View_by_ID(false,System.out);break;
            }
            case 3:{
                manager_system.View_by_GPA(true,System.out);break;
            }
            case 4:{
                manager_system.View_by_GPA(false,System.out);break;
            }
            case 5:{
                return -1;
            }
        }
        return 1;
    }
    private boolean Order_Menu(){
        System.out.println(ConsoleColors.BLUE_BOLD + "STUDENT MANAGER");
        System.out.println(ConsoleColors.WHITE_BOLD+"Choose Order:");
        System.out.println(ConsoleColors.WHITE +"1. Ascending");
        System.out.println(ConsoleColors.WHITE +"2. Descending");
        int selected = getIndexCommand(1,2);
        if (selected == 1) return true;
        return false;
    }
    private int ImportExport_Menu() throws Exception {
        ConsoleColors.clearConsole();
        System.out.println(ConsoleColors.BLUE_BOLD + "STUDENT MANAGER");
        System.out.println(ConsoleColors.YELLOW_BOLD+"IMPORT/EXPORT");
        System.out.println(ConsoleColors.WHITE+"1. Import Student List from .CSV file");
        System.out.println(ConsoleColors.WHITE+"2. Export Student List to .CSV file");
        System.out.println(ConsoleColors.WHITE_BOLD+"3. Back");
        int selected = getIndexCommand(1,3);
        switch (selected){
            case 3:{
                return -1;
            }
            case 1:{
                Import();
                break;
            }
            case 2:{
                Export();
                break;
            }
        }
        return 1;
    }

    public static void main(String[] args) throws Exception {
        Application app = new Application();
        int state = 0;
        do{
        state = app.Menu_Start();
        }while(state == 1);
    }

    private void Add(){
        System.out.println("***** ADD New Student *****\n");
        Scanner scanner = new Scanner(new InputStreamReader(System.in));

        Student student = new Student();
        System.out.print("ID: ");
        String str = scanner.nextLine();
        student.setID(str);
        System.out.print("Name: ");
        str = scanner.nextLine();
        student.setName(str);
        System.out.print("Address: ");
        str = scanner.nextLine();
        student.setAddress(str);
        System.out.print("GPA: ");
        Double db = scanner.nextDouble();
        student.setGPA(db);
        System.out.print("Note: ");
        str = scanner.nextLine();
        student.setNotes(str);

        if(manager_system.AddStudent(student)){
            System.out.println("Add new Student Successfully");
        }else {
            System.out.println("Fail to Add Student!!");
        }
    }
    private void Delete(){
        System.out.println("***** Remove New Student *****\n");
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        System.out.println("Enter Student ID: ");
        if(manager_system.RemoveStudent_by_ID(scanner.nextLine())) {
            System.out.println("Remove Successful!");
        }else {
            System.out.println("Fail to Remove Student!");
        }
        scanner.close();
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
