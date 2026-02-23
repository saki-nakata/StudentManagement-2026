package raisetech.StudentManagement2026ver.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement2026ver.data.Student;
import raisetech.StudentManagement2026ver.data.StudentCourse;
import raisetech.StudentManagement2026ver.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    List<Student> studentList = repository.students();
    return studentList.stream()
        .filter(student -> student.getAge() >= 30 && student.getAge() < 40)
        .toList();
  }

  public List<StudentCourse> searchCourseList() {
    List<StudentCourse> courseList = repository.courses();
    return courseList.stream()
        .filter(course -> course.getCourseName().equals("Java"))
        .toList();
  }
  
}
