package ui;

import entity.Student;
import service.StudentService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by sailesh on 1/4/22.
 */
public class StudentUI {

    private Scanner scanner = new Scanner(System.in);

    private StudentService studentService = new StudentService();

    public void createStudentScreen() {
        Boolean isRepeat = true;
        System.out.println("STUDENT MANAGEMENT SYSTEM");
        System.out.println("Welcome to Admin Panel!");
        System.out.println();
        System.out.println();

        while (isRepeat) {
            Student student = new Student();

            isRepeat = false;
            System.out.println("Please enter the student details");
            System.out.println();

            System.out.print("Name \t");
            student.setName(scanner.nextLine());

            System.out.print("Address \t");
            student.setAddress(scanner.nextLine());

            Boolean reAskAge = false;
            do {
//                try {
//                    System.out.print("Age \t");
//                    student.setAge(scanner.nextInt());
//                    System.out.println(scanner.nextLine());
//                    reAskAge= false;
//                } catch (InputMismatchException e) {
//                    System.out.println(scanner.nextLine());
//                    reAskAge = true;
//                }

                try {
                    System.out.print("Age \t");
                    student.setAge(Integer.parseInt(scanner.nextLine()));
                    reAskAge= false;
                } catch (NumberFormatException e) {
                    reAskAge = true;
                }
            } while (reAskAge);


            System.out.print("Gender \t");
            student.setGender(scanner.nextLine());

            Boolean reAskDate = false;
            do {
                System.out.print("Date(dd/MM/yyyy) \t");
                String dateStr = scanner.nextLine();

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    student.setDob(sdf.parse(dateStr));
                    reAskDate= false;
                } catch (ParseException e) {
                    System.out.println("Please enter date in specified format.");
                    reAskDate = true;
                }
            } while (reAskDate);


            Student savedStudent = studentService.createStudent(student);
            System.out.println("Student created successfully.");
            System.out.println(savedStudent);
            System.out.println("do you want to create another student. Press 'y' for yes");
            String performAgain = scanner.nextLine();
            if (performAgain.equalsIgnoreCase("y")) {
                isRepeat = true;
            }


        }

    }

    public void updateStudentScreen() {
        Boolean isRepeat = true;
        System.out.println("STUDENT MANAGEMENT SYSTEM");
        System.out.println("Welcome to Admin Panel!");
        System.out.println();
        System.out.println();

        while (isRepeat) {
            System.out.println("Please provide id of the student for which you want to update information");
            Integer studentId = scanner.nextInt();
            Student student = studentService.findStudent(studentId);
            if(student != null) {
                scanner.nextLine();
                System.out.println("Please enter the new details for " +student.getName());
                System.out.print("Name \t");
                student.setName(scanner.nextLine());

                System.out.print("Address \t");
                student.setAddress(scanner.nextLine());

                Boolean reAskAge = false;
                do {
//                try {
//                    System.out.print("Age \t");
//                    student.setAge(scanner.nextInt());
//                    System.out.println(scanner.nextLine());
//                    reAskAge= false;
//                } catch (InputMismatchException e) {
//                    System.out.println(scanner.nextLine());
//                    reAskAge = true;
//                }

                    try {
                        System.out.print("Age \t");
                        student.setAge(Integer.parseInt(scanner.nextLine()));
                        reAskAge= false;
                    } catch (NumberFormatException e) {
                        reAskAge = true;
                    }
                } while (reAskAge);


                System.out.print("Gender \t");
                student.setGender(scanner.nextLine());

                Boolean reAskDate = false;
                do {
                    System.out.print("Date(dd/MM/yyyy) \t");
                    String dateStr = scanner.nextLine();

                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        student.setDob(sdf.parse(dateStr));
                        reAskDate= false;
                    } catch (ParseException e) {
                        System.out.println("Please enter date in specified format.");
                        reAskDate = true;
                    }
                } while (reAskDate);

                studentService.updateStudent(student);
            } else {
                System.out.println("Couldn't find the student in our record. Please recheck");
            }



            System.out.println("Do you want to find other student as well. Press 'y' for continue");
            String performAgain = scanner.nextLine();
            if(!performAgain.equals("y")) {
                isRepeat = false;
            }
        }

    }

    public void findStudentScreen() {
        Boolean isRepeat = true;
        System.out.println("STUDENT MANAGEMENT SYSTEM");
        System.out.println("Welcome to Admin Panel!");
        System.out.println();
        System.out.println();

        while (isRepeat) {
            System.out.println("Please Select any one of the operation");
            System.out.println("1. Find student by id");
            System.out.println("2. Find student by name");

            Integer choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Please enter the student id");
                    Student student = studentService.findStudent(scanner.nextInt());
                    System.out.println(student);
                    break;
                case 2:
                    System.out.println("Please enter the student name");
                    scanner.nextLine();
                    List<Student> students = studentService.findStudent(scanner.nextLine());

                    if(students !=  null && !students.isEmpty()) {
                        for(Student student1 : students) {
                            System.out.println(student1);

                        }
                    } else {
                        System.out.println("Sorry no user found by that name");
                    }
                    break;



            }

            System.out.println("Do you want to find other student as well. Press 'y' for continue");
            String performAgain = scanner.nextLine();
            if(!performAgain.equals("y")) {
                isRepeat = false;
            }
        }

    }

    public void findAllStudentScreeen() {

        System.out.println("STUDENT MANAGEMENT SYSTEM");
        System.out.println("Welcome to Admin Panel!");
        System.out.println();
        System.out.println();

        List<Student> students = studentService.findAllStudent();
        System.out.println("StudentId \t\t\t\t Name\t\t\t\tAge\t\t\t\tAddress\t\t\t\tGender\t\t\t\tDOB");
        for(Student student : students) {
            System.out.println(student.getId()+"\t\t\t\t\t" + student.getName() + "\t\t\t\t\t" + student.getAge() + "\t\t\t\t\t" + student.getAddress() + "\t\t\t\t\t" +student.getGender() + "\t\t\t\t\t" +student.getDob());
        }
    }

    public void deleteStudentScreeen() {
        Boolean isRepeat = true;
        System.out.println("STUDENT MANAGEMENT SYSTEM");
        System.out.println("Welcome to Admin Panel!");
        System.out.println();
        System.out.println();

        while (isRepeat) {
            System.out.println("Please provide id of the student for which you want to delete information");
            Integer studentId = scanner.nextInt();
            Student student = studentService.findStudent(studentId);
            if(student != null) {
                studentService.deleteStudent(student);
                System.out.println("Student deleted successfully");
            } else {
                System.out.println("Couldn't find the student in our record. Please recheck");
            }



            System.out.println("Do you want to delete other student as well. Press 'y' for continue");
            String performAgain = scanner.nextLine();
            if(!performAgain.equals("y")) {
                isRepeat = false;
            }
        }

    }
}


//application
//studentUI
//studentservice
//studentRepository
//logic and save
//Common utils FileUtils(save)