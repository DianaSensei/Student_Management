import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Application {
    Boolean isLogin;
    private void Menu_Start(){
        System.out.println(ConsoleColors.BLUE_BOLD + "Student Manager");
        System.out.println(ConsoleColors.WHITE+"1. Load Student List from .CSV file");
        System.out.println(ConsoleColors.WHITE+"2. Save Student List to .CSV file");
        System.out.println(ConsoleColors.WHITE+"3. View Student List with ID");
        System.out.println(ConsoleColors.WHITE+"4. View Student List with GPA");
        System.out.println(ConsoleColors.WHITE+"5. Add new Student to List");
        System.out.println(ConsoleColors.WHITE+"6. Delete Student from List");
        System.out.println(ConsoleColors.WHITE+"7. Import/Export Student List from/to .CSV file");
    }

    public static void main(String[] args) {
        Application app = new Application();
        Manager_System system = new Manager_System();
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        int selected = 0;
        boolean flag = false;
        app.Menu_Start();
        ConsoleColors.clearConsole();
        do {
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
            if(!flag)
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
                        System.out.println("Select " + selected);
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
                        System.out.println("Select " + selected);
                        break;
                    }
                    default: {
                        ConsoleColors.clearConsole();
                        flag = true;
                        System.out.println("Invalid command index!!");
                    }
            }
        } while (flag);
    }
}
