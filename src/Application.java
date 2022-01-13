import ui.ResultUI;
import ui.StudentUI;
import java.util.Scanner;

/**
 * Created by sailesh on 1/4/22.
 */
public class Application {
   static Scanner scanner = new Scanner(System.in);
   private static final String USERNAME = "sailesh";
   private static final String PASSWORD = "test";

    public static void main(String[] args) {
        Boolean isRepeat = true;
        System.out.println("STUDENT MANAGEMENT SYSTEM");
        System.out.println("Welcome to Admin Panel!");
        System.out.println();
        System.out.println();

        while(isRepeat) {

            System.out.println("Please enter your credentials");
            System.out.print("Username: \t");
            String username = scanner.nextLine();
            System.out.println();

            System.out.print("Password: \t");
            String password = scanner.nextLine();
            System.out.println();

            if(username.equals(USERNAME) && password.equals(PASSWORD)) {
                isRepeat = false;
                System.out.println("Valid credentials. Logged in successfully!");
                System.out.println();
                System.out.println();
                operationScreen();
            } else {
                System.out.println("Invalid credentials. Please try again");
            }
        }

    }

    public static void operationScreen() {

        Boolean isRepeat = true;
        System.out.println("STUDENT MANAGEMENT SYSTEM");
        System.out.println("Welcome to Admin Panel!");
        System.out.println();
        System.out.println();

        while (isRepeat) {
            isRepeat = false;
            System.out.println("Please choose on of the operations:");
            System.out.println("1. Manage Student");
            System.out.println("3. Manage Results");

            Integer choice = scanner.nextInt();

            switch (choice) {
                case 1: manageStudentScreen();
                    break;
                case 2: manageExamMarksScreen();
                    break;

                default:
                    System.out.println("Invalid operation selected. Please try again !");
                    isRepeat = true;
            }
        }
    }


    public static void manageStudentScreen() {
        StudentUI studentUI = new StudentUI();
        Boolean isRepeat = true;
        System.out.println("STUDENT MANAGEMENT SYSTEM");
        System.out.println("Welcome to Admin Panel!");
        System.out.println();
        System.out.println();

        while (isRepeat) {
            isRepeat = false;
            System.out.println("Please choose on of the operations:");
            System.out.println("1. Create Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Find student");
            System.out.println("5. Find  all students");



            Integer choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: studentUI.createStudentScreen();
                    break;
                case 2: studentUI.updateStudentScreen();
                    break;
                case 3:  studentUI.deleteStudentScreeen();
                    break;
                case 4: studentUI.findStudentScreen();
                    break;

                case 5: studentUI.findAllStudentScreeen();
                    break;


                default:
                    System.out.println("Invalid operation selected. Please try again !");
                    isRepeat = true;
            }


            System.out.println("Do you want to perform other operation on student?. If press y");
            String choice1 = scanner.nextLine();
            if("y".equalsIgnoreCase(choice1)) {
                isRepeat = true;
            }

        }

    }

    public static void  manageExamMarksScreen() {
        ResultUI resultUI = new ResultUI();
        Boolean isRepeat = true;
        System.out.println("STUDENT MANAGEMENT SYSTEM");
        System.out.println("Welcome to Admin Panel!");
        System.out.println();
        System.out.println();

        while (isRepeat) {
            isRepeat = false;
            System.out.println("Please choose on of the operations:");
            System.out.println("1. Insert marks for student");
            System.out.println("2. Display result");



            Integer choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: resultUI.insertMarksScreen();
                    break;
                case 2: resultUI.displayResult();
                    break;


                default:
                    System.out.println("Invalid operation selected. Please try again !");
                    isRepeat = true;
            }


            System.out.println("Do you want to perform other operation on student?. If press y");
            String choice1 = scanner.nextLine();
            if("y".equalsIgnoreCase(choice1)) {
                isRepeat = true;
            }

        }
    }



}


