package raisetech.StudentManagement2026ver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement2026ver.controller.converter.StudentConverter;
import raisetech.StudentManagement2026ver.data.Student;
import raisetech.StudentManagement2026ver.data.StudentCourse;
import raisetech.StudentManagement2026ver.domain.StudentDetail;
import raisetech.StudentManagement2026ver.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/students")
  public List<Student> getStudents() {
    return service.searchStudentList();
  }

  @GetMapping("/details")
  public List<StudentDetail> getStudentDetail() {
    List<Student> students = service.searchStudentList();
    List<StudentCourse> courses = service.searchCourseList();

    return converter.convertStudentDetails(students, courses);

  }

  @GetMapping("/courses")
  public List<StudentCourse> getCourses() {
    return service.searchCourseList();
  }

}
