import java.io.File;
import java.util.Scanner;

public class Application {
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

    private int Menu_Start() throws Exception {
        ConsoleColors.clearConsole();
        System.out.println(ConsoleColors.BLUE_BOLD + "STUDENT MANAGER");
        System.out.println(ConsoleColors.GREEN + "----------------------------");
        System.out.println(ConsoleColors.WHITE + "1. Load/Save Student List from/to file");
        System.out.println(ConsoleColors.WHITE + "2. View Student List");
        System.out.println(ConsoleColors.WHITE + "3. Add new Student to List");
        System.out.println(ConsoleColors.WHITE + "4. Delete Student from List");
        System.out.println(ConsoleColors.WHITE + "5. Import/Export Student List from/to .CSV file");
        System.out.println(ConsoleColors.WHITE_BOLD+"6. Quit");
        System.out.println(ConsoleColors.GREEN + "----------------------------"+ConsoleColors.RESET);
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
    private int View_Menu() throws Exception {
        ConsoleColors.clearConsole();
        System.out.println(ConsoleColors.BLUE_BOLD + "STUDENT MANAGER");
        System.out.println(ConsoleColors.YELLOW_BOLD+"VIEW");
        System.out.println(ConsoleColors.GREEN + "----------------------------");
        System.out.println(ConsoleColors.WHITE+"1. View Student List with ID Ascending");
        System.out.println(ConsoleColors.WHITE+"2. View Student List with ID Descending");
        System.out.println(ConsoleColors.WHITE+"3. View Student List with GPA Ascending");
        System.out.println(ConsoleColors.WHITE+"4. View Student List with GPA Descending");
        System.out.println(ConsoleColors.WHITE_BOLD+"5. Back");
        System.out.println(ConsoleColors.GREEN + "----------------------------"+ConsoleColors.RESET);
        int selected = getIndexCommand(1,3);
        switch (selected){
            case 1:{
                View(0,true);
                if(AddDelete_Menu() == -1 ) break;
            }
            case 2:{
                View(0,false);
                if(AddDelete_Menu() == -1 ) break;
            }
            case 3:{
                View(1,true);
                if(AddDelete_Menu() == -1 ) break;
            }
            case 4:{
                View(1,false);
                if(AddDelete_Menu() == -1 )break;
            }
            case 5:{
                return -1;
            }
        }
        return 1;
    }
    private int LoadSave_Menu() throws Exception{
        ConsoleColors.clearConsole();
        System.out.println(ConsoleColors.BLUE_BOLD + "STUDENT MANAGER");
        System.out.println(ConsoleColors.YELLOW_BOLD+"Load/Save");
        System.out.println(ConsoleColors.GREEN + "----------------------------");
        System.out.println(ConsoleColors.WHITE+"1. Load Student List from file");
        System.out.println(ConsoleColors.WHITE+"2. Save Student List to file");
        System.out.println(ConsoleColors.WHITE_BOLD+"3. Back");
        System.out.println(ConsoleColors.GREEN + "----------------------------"+ConsoleColors.RESET);
        int selected = getIndexCommand(1,3);
        switch (selected){
            case 3:{
                return -1;
            }
            case 1:{
                if(!Load()) System.out.println("Fail to Save to file!");
                break;
            }
            case 2:{
                Save();
                break;
            }
        }
        return 1;
    }
    private int AddDelete_Menu()throws Exception{
        System.out.println(ConsoleColors.GREEN + "----------------------------");
        System.out.println(ConsoleColors.WHITE + "1. Add new Student to List");
        System.out.println(ConsoleColors.WHITE + "2. Delete Student from List");
        System.out.println(ConsoleColors.WHITE_BOLD+"3. Back");
        System.out.println(ConsoleColors.GREEN + "----------------------------"+ConsoleColors.RESET);
        int selected = getIndexCommand(1,2);
        switch (selected){
            case 1:{
                Add();break;
            }
            case 2:{
                Delete();break;
            }
        }
        return -1;
    }
    private int ImportExport_Menu() throws Exception {
        ConsoleColors.clearConsole();
        System.out.println(ConsoleColors.BLUE_BOLD + "STUDENT MANAGER");
        System.out.println(ConsoleColors.YELLOW_BOLD+"IMPORT/EXPORT");
        System.out.println(ConsoleColors.GREEN + "----------------------------");
        System.out.println(ConsoleColors.WHITE+"1. Import Student List from .CSV file");
        System.out.println(ConsoleColors.WHITE+"2. Export Student List to .CSV file");
        System.out.println(ConsoleColors.WHITE_BOLD+"3. Back");
        System.out.println(ConsoleColors.GREEN + "----------------------------"+ConsoleColors.RESET);
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

    private void View(int type,boolean order){
        System.out.println(ConsoleColors.BLUE_BOLD + "STUDENT MANAGER");
        System.out.println(ConsoleColors.YELLOW_BOLD+"View"+ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + "----------------------------"+ConsoleColors.RESET);
        if(type == 1 ) manager_system.View_by_GPA(order);
        if(type == 0 ) manager_system.View_by_ID(order);
    }
    private void Add(){
        System.out.println("***** ADD New Student *****\n");
        Scanner scanner = new Scanner(System.in);

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
        str = scanner.nextLine();
        student.setGPA(Double.parseDouble(str));
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Student ID: ");
        if(manager_system.RemoveStudent_by_ID(scanner.nextLine())) {
            System.out.println("Remove Successful!");
        }else {
            System.out.println("Fail to Remove Student!");
        }
    }
    private boolean Load() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Filename: ");
        String name = scanner.nextLine();
        File temp = new File(name);
        if(!temp.exists()) return false;
        manager_system.Load(name);
        return true;
    }
    private void Save() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Filename: ");
        String name = scanner.nextLine();
        manager_system.Save(name);
    }
    private void Import(){
// TO DO
    }
    private void Export(){
// TO DO
    }
}