package ui;

import entity.Result;
import entity.Student;
import entity.SubjectMark;
import model.Subject;
import service.ExamResultService;
import service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by sailesh on 1/12/22.
 */
public class ResultUI {
    private Scanner scanner =new Scanner(System.in);
    private StudentService studentService = new StudentService();
    private ExamResultService examResultService = new ExamResultService();

    public void insertMarksScreen() {
        Boolean isRepeat = true;
        System.out.println("STUDENT MANAGEMENT SYSTEM");
        System.out.println("Welcome to Admin Panel!");
        System.out.println();
        System.out.println();

        while (isRepeat) {
            System.out.println("Please provide id of the student for which you want to insert MArks");
            Integer studentId = scanner.nextInt();
            Student student = studentService.findStudent(studentId);
            Map<Integer, Double> subjectMarkMap = new HashMap<>();
            if(student != null) {
                System.out.println("Enter the marks for English");
                subjectMarkMap.put(Subject.ENGLISH.getKey(), scanner.nextDouble());

                System.out.println("Enter the marks for Nepali");
                subjectMarkMap.put(Subject.NEPALI.getKey(), scanner.nextDouble());

                System.out.println("Enter the marks for Science");
                subjectMarkMap.put(Subject.SCIENCE.getKey(), scanner.nextDouble());

                System.out.println("Enter the marks for Math");
                subjectMarkMap.put(Subject.MATH.getKey(), scanner.nextDouble());

                examResultService.insertMarks(studentId, subjectMarkMap);


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

    public void displayResult() {

        Boolean isRepeat = true;
        System.out.println("STUDENT MANAGEMENT SYSTEM");
        System.out.println("Welcome to Admin Panel!");
        System.out.println();
        System.out.println();

        while (isRepeat) {
            System.out.println("Please provide id of the student for which you want to Display Result");
            Integer studentId = scanner.nextInt();
            Student student = studentService.findStudent(studentId);
            if(student != null) {
                Result result = examResultService.displayResult(student);
               if(result == null) {
                   System.out.println("Sorry we couldnot find the result for the student : " + student.getName());
               } else {
                   printResult(result);
               }
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

    private void printResult(Result result) {
        Student student = result.getStudent();
        System.out.println("\t\t\t\t\t\t\t Report Card ");
        System.out.println();
        System.out.println();
        System.out.println("Name: " + student.getName() + "\t\t\t\t\t\t\t\t\t\t  StudentId:" + student.getId() );
        System.out.println("Address: " + student.getAddress() + "\t\t\t\t\t\t\t\t  DOB:" + student.getDob());
        System.out.println();
        System.out.println();
        System.out.println("Subject \t\t\t\t\t\t\t Total Mark \t\t\t\t\t\t\t  Mark Obtained");
        List<SubjectMark> subjectMarkList = result.getSubjectMarkList();
        for(SubjectMark subjectMark : subjectMarkList) {
            String subject = Subject.lookupByKey(subjectMark.getSubjectId()).getDescription();
            System.out.println(subject + "\t\t\t\t\t\t\t\t\t 100 " + "\t\t\t\t\t\t\t\t\t" + subjectMark.getMarks() );
        }
        System.out.println();
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Total: "  + result.getTotalMarksObtained());
        System.out.println("Percentage: " + result.getPercentage() + "\t\t\t\t\t\t\t\t\t \t\t\tStatus:" + result.getStatus());


    }
}


///
