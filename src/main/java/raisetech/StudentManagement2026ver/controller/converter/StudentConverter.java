package raisetech.StudentManagement2026ver.controller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import raisetech.StudentManagement2026ver.data.Student;
import raisetech.StudentManagement2026ver.data.StudentCourse;
import raisetech.StudentManagement2026ver.domain.StudentDetail;

/**
 * 受講生情報とコース情報を受講生詳細に変換するConverterです。
 */
@Component
public class StudentConverter {

  /**
   * 受講生情報とコース情報を紐づけて受講生詳細一覧を作成します。
   * <p>
   * 各受講生に対応するコース情報を設定します。
   *
   * @param students 受講生情報一覧
   * @param courses  コース情報一覧
   * @return 受講生詳細一覧
   */
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
