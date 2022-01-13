package Repository;

import entity.SubjectMark;

import java.util.List;

/**
 * Created by sailesh on 1/12/22.
 */
public interface SubjectMarkRepository {

    List<SubjectMark> createSubjectMark(List<SubjectMark> subjectMarks);
    List<SubjectMark> getAllSubjectMarks(Integer studentId);
}
