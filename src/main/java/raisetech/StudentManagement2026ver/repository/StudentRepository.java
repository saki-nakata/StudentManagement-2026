package raisetech.StudentManagement2026ver.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import raisetech.StudentManagement2026ver.data.Student;
import raisetech.StudentManagement2026ver.data.StudentCourse;

/**
 * 受講生テーブルおよび受講生コーステーブルを操作するRepositoryです。
 */
@Mapper
public interface StudentRepository {

  /**
   * 受講生情報を全件取得します。
   *
   * @return 受講生情報一覧
   */
  List<Student> getStudents();

  /**
   * コース情報を全件取得します。
   *
   * @return コース情報一覧
   */
  List<StudentCourse> getCourses();

  /**
   * 指定した受講生IDの受講生情報を取得します。
   *
   * @param id 受講生ID
   * @return 受講生情報
   */
  Student getStudent(int id);

  /**
   * 指定した受講生IDに紐づくコース情報を取得します。
   *
   * @param studentId 受講生ID
   * @return 受講生IDに紐づくコース情報一覧
   */
  List<StudentCourse> getCoursesByStudentId(int studentId);

  /**
   * 受講生情報を登録します。 IDは自動採番で設定されます。
   *
   * @param student 受講生情報
   */
  void registerStudent(Student student);

  /**
   * コース情報を登録します。 IDは自動採番で設定されます。
   *
   * @param course コース情報
   */
  void registerCourse(StudentCourse course);

  /**
   * 受講生情報を更新します。
   *
   * @param student 受講生情報
   */
  void updateStudent(Student student);

  /**
   * コース情報を更新します。
   *
   * @param course コース情報
   */
  void updateCourse(StudentCourse course);

}
