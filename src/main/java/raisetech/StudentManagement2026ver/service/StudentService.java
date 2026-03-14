package raisetech.StudentManagement2026ver.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement2026ver.data.Student;
import raisetech.StudentManagement2026ver.data.StudentCourse;
import raisetech.StudentManagement2026ver.domain.StudentDetail;
import raisetech.StudentManagement2026ver.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    return repository.students();
  }

  public List<StudentCourse> searchCourseList() {
    return repository.courses();
  }

  @Transactional
  public void registerStudentDetail(StudentDetail detail) {
    repository.registerStudent(detail.getStudent());

    StudentCourse course = detail.getCourses().get(0);
    course.setStudentId(detail.getStudent().getId());
    course.setEndDate(course.getStartDate().plusYears(1));
    repository.registerCourse(course);

  }

  public StudentDetail getDetail(int id) {
    StudentDetail detail = new StudentDetail();
    detail.setStudent(repository.getStudent(id));
    detail.setCourses(repository.getCourse(id));
    return detail;
  }

  @Transactional
  public void updateStudentDetail(StudentDetail detail) {
    repository.updateStudent(detail.getStudent());
    if (detail.getCourses() != null) {
      for (StudentCourse course : detail.getCourses()) {
        repository.updateCourse(course);
      }
    }
  }

}
