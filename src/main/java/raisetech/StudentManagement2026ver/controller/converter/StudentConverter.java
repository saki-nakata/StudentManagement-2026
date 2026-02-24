package raisetech.StudentManagement2026ver.controller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import raisetech.StudentManagement2026ver.data.Student;
import raisetech.StudentManagement2026ver.data.StudentCourse;
import raisetech.StudentManagement2026ver.domain.StudentDetail;

@Component
public class StudentConverter {

  public List<StudentDetail> convertStudentDetails(List<Student> students,
      List<StudentCourse> courses) {
    List<StudentDetail> studentDetails = new ArrayList<>();
    students.forEach(student -> {
      StudentDetail detail = new StudentDetail();
      detail.setStudent(student);
      List<StudentCourse> studentCourses = courses.stream()
          .filter(course -> student.getId() == course.getStudentId()).collect(Collectors.toList());
      detail.setCourses(studentCourses);
      studentDetails.add(detail);
    });
    return studentDetails;
  }
}
