package raisetech.StudentManagement2026ver.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement2026ver.controller.converter.StudentConverter;
import raisetech.StudentManagement2026ver.data.Student;
import raisetech.StudentManagement2026ver.data.StudentCourse;
import raisetech.StudentManagement2026ver.domain.StudentDetail;
import raisetech.StudentManagement2026ver.repository.StudentRepository;

/**
 * 受講生情報およびコース情報の取得・登録・更新処理を行うServiceです。
 */
@Service
public class StudentService {

  private StudentRepository repository;
  private StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository repository, StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  /**
   * 受講生詳細一覧を取得します。
   *
   * @return 受講生情報一覧
   */
  public List<StudentDetail> getStudentDetails() {
    List<Student> students = repository.getStudents();
    List<StudentCourse> courses = repository.getCourses();
    return converter.convertStudentDetails(students, courses);
  }

  /**
   * 指定した受講生IDに紐づく受講生詳細を取得します。
   *
   * @param id 受講生ID
   * @return 受講生詳細
   */
  public StudentDetail getStudentDetail(int id) {
    Student student = repository.getStudent(id);
    List<StudentCourse> courses = repository.getCoursesByStudentId(id);
    return new StudentDetail(student, courses);
  }

  /**
   * 受講生詳細を登録します。 受講生情報とコース情報をそれぞれ登録します。
   *
   * @param detail 受講生詳細
   */
  @Transactional
  public void registerStudentDetail(StudentDetail detail) {
    Student student = detail.getStudent();
    repository.registerStudent(student);
    StudentCourse course = detail.getCourses().get(0);
    initCourse(course, student);
    repository.registerCourse(course);
  }

  /**
   * コース情報登録時の初期値を設定します。
   *
   * @param course  コース情報
   * @param student 受講生情報
   */
  private void initCourse(StudentCourse course, Student student) {
    course.setStudentId(student.getId());
    course.setEndDate(course.getStartDate().plusYears(1));
  }

  /**
   * 受講生詳細を更新します。 受講生情報およびコース情報をそれぞれ更新します。
   *
   * @param detail 受講生詳細
   */
  @Transactional
  public void updateStudentDetail(StudentDetail detail) {
    repository.updateStudent(detail.getStudent());
    if (detail.getCourses() != null) {
      detail.getCourses().forEach(course -> repository.updateCourse(course));
    }
  }

}
