package service;

import Repository.FileSubjectMarkRepository;
import Repository.SubjectMarkRepository;
import entity.Result;
import entity.Student;
import entity.SubjectMark;
import model.Status;
import utils.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sailesh on 1/4/22.
 */
public class ExamResultService {
    private static final String FILE_NAME = "subjectMark.txt";

    SubjectMarkRepository subjectMarkRepository = new FileSubjectMarkRepository();


    public void insertMarks(Integer studentId , Map<Integer, Double> subjectMarksMap) {

        List<SubjectMark> subjectMarkList = new ArrayList<>();
        Integer lastIndex = FileUtil.getLastUniqueIdentifier(FILE_NAME);

        if(lastIndex == null) {
            lastIndex = 0;
        }

        for(Integer subjectId : subjectMarksMap.keySet()) {
            Double marks = subjectMarksMap.get(subjectId);

            SubjectMark subjectMark = new SubjectMark();
            subjectMark.setSubjectMarkID(++lastIndex);
            subjectMark.setStudentId(studentId);
            subjectMark.setSubjectId(subjectId);
            subjectMark.setMarks(marks);
            subjectMarkList.add(subjectMark);
        }

        subjectMarkRepository.createSubjectMark(subjectMarkList);



    }

    public Result displayResult(Student student) {
        //create result object
        //fetch subject mark for that student
        //.calculate total marks
        //calculate percentage
        //calculate status

        //fail criteria : less than 40 on each subject or less than 45%

        Result result = new Result();

        List<SubjectMark> subjectMarkList = subjectMarkRepository.getAllSubjectMarks(student.getId());
        if(subjectMarkList == null && !subjectMarkList.isEmpty()) {
            return null;
        }

        result.setStudent(student);
        result.setSubjectMarkList(subjectMarkList);
        Integer totalSubjects = subjectMarkList.size();
        Double totalMarksObtained = 0D;
        Status status = Status.PASS;
        for(SubjectMark subjectMark : subjectMarkList) {
            totalMarksObtained += subjectMark.getMarks();
            if(subjectMark.getMarks() < 40) {
                status = Status.FAIL;
            }

        }

        result.setTotalMarksObtained(totalMarksObtained);

        Double percentage = totalMarksObtained/totalSubjects;

        if(percentage < 45) {
            status = Status.FAIL;
        }

        result.setStatus(status);

        result.setPercentage(percentage);

        return  result;
    }

}

