package raisetech.StudentManagement2026ver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement2026ver.data.Student;
import raisetech.StudentManagement2026ver.data.StudentCourse;
import raisetech.StudentManagement2026ver.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping("/students")
  public List<Student> getStudents() {
    return service.searchStudentList();
  }

  @GetMapping("/courses")
  public List<StudentCourse> getCourses() {
    return service.searchCourseList();
  }

}
