package raisetech.StudentManagement2026ver.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement2026ver.controller.converter.StudentConverter;
import raisetech.StudentManagement2026ver.data.Student;
import raisetech.StudentManagement2026ver.data.StudentCourse;
import raisetech.StudentManagement2026ver.domain.StudentDetail;
import raisetech.StudentManagement2026ver.exception.NotFoundException;
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
   * @return 受講生詳細一覧
   */
  public List<StudentDetail> getStudentDetails() {
    List<Student> students = repository.getStudents();
    List<StudentCourse> courses = repository.getCourses();
    return converter.convertStudentDetails(students, courses);
  }

  /**
   * 指定した受講生IDに紐づく受講生詳細を取得します。
   * <p>
   * 指定した受講生IDが存在しない場合は例外が発生します。
   *
   * @param id 受講生ID
   * @return 受講生詳細
   */
  public StudentDetail getStudentDetail(int id) {
    Student student = repository.getStudent(id);
    if (student == null) {
      throw new NotFoundException("受講生ID " + id + " に該当するデータは見つかりませんでした。");
    }
    List<StudentCourse> courses = repository.getCoursesByStudentId(id);
    return new StudentDetail(student, courses);
  }

  /**
   * 受講生詳細を登録します。
   * <p>
   * 登録対象の受講生情報が null の場合は例外が発生します。 コース情報が null または空の場合は登録されません。
   *
   * @param detail 受講生詳細
   * @return 登録後の受講生詳細
   */
  @Transactional
  public StudentDetail registerStudentDetail(StudentDetail detail) {
    Student student = detail.getStudent();
    if (student == null) {
      throw new IllegalArgumentException("登録する受講生情報がありません。");
    }
    repository.registerStudent(student);
    if (detail.getCourses() != null && !detail.getCourses().isEmpty()) {
      StudentCourse course = detail.getCourses().get(0);
      initCourse(course, student.getId());
      repository.registerCourse(course);
    }
    return detail;
  }

  /**
   * コース情報登録時の初期値を設定します。
   *
   * @param course    コース情報
   * @param studentId 受講生ID
   */
  void initCourse(StudentCourse course, int studentId) {
    course.setStudentId(studentId);
    course.setEndDate(course.getStartDate().plusYears(1));
  }

  /**
   * 受講生詳細を更新します。
   *
   * @param detail 受講生詳細
   * @param id     受講生ID
   * @return 更新後の受講生詳細
   */
  @Transactional
  public StudentDetail updateStudentDetail(StudentDetail detail, int id) {
    updateStudent(detail.getStudent(), id);
    updateCourses(detail.getCourses(), id);
    return detail;
  }

  /**
   * 受講生情報を更新します。
   * <p>
   * 受講生情報がnullの場合は何もせず終了します。 指定した受講生IDが存在しない場合は例外が発生します。
   *
   * @param student 受講生情報
   * @param id      受講生ID
   */
  void updateStudent(Student student, int id) {
    if (student == null) {
      return;
    }
    student.setId(id);
    int updated = repository.updateStudent(student);
    if (updated == 0) {
      throw new NotFoundException(
          "受講生ID " + student.getId() + " に該当するデータは見つかりませんでした。");
    }
  }

  /**
   * コース情報を更新します。
   * <p>
   * コース情報一覧がnullの場合は何もせず終了します。 指定したコースIDが存在しない場合は例外が発生します。
   *
   * @param courses コース情報一覧
   * @param id      受講生ID
   */
  void updateCourses(List<StudentCourse> courses, int id) {
    if (courses == null) {
      return;
    }
    for (StudentCourse course : courses) {
      course.setStudentId(id);
      int updated = repository.updateCourse(course);
      if (updated == 0) {
        throw new NotFoundException(
            "コースID " + course.getId() + " に該当するデータは見つかりませんでした。");
      }
    }
  }

}
