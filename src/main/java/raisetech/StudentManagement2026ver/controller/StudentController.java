package raisetech.StudentManagement2026ver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @GetMapping("/list")
  public List<StudentDetail> getStudentDetails() {
    List<Student> students = service.searchStudentList();
    List<StudentCourse> courses = service.searchCourseList();
    return converter.convertStudentDetails(students, courses);
  }

  @GetMapping("/student/{id}")
  public StudentDetail getStudentDetail(@PathVariable int id) {
    return service.getDetail(id);
  }

  @PostMapping("register")
  public ResponseEntity<StudentDetail> registerStudentDetail(@RequestBody StudentDetail detail) {
    service.registerStudentDetail(detail);
    return ResponseEntity.ok(detail);
  }

  @PostMapping("update")
  public ResponseEntity<StudentDetail> updateStudentDetail(@RequestBody StudentDetail detail) {
    service.updateStudentDetail(detail);
    return ResponseEntity.ok().body(detail);
  }

}
