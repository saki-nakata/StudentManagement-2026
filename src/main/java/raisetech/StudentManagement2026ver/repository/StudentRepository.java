package raisetech.StudentManagement2026ver.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
  @Select("SELECT * FROM students")
  List<Student> getStudents();

  /**
   * コース情報を全件取得します。
   *
   * @return コース情報一覧
   */
  @Select("SELECT * FROM students_courses")
  List<StudentCourse> getCourses();

  /**
   * 指定した受講生IDの受講生情報を取得します。
   *
   * @param id 受講生ID
   * @return 受講生情報
   */
  @Select("SELECT * FROM students WHERE id = #{id}")
  Student getStudent(int id);

  /**
   * 指定した受講生IDに紐づくコース情報を取得します。
   *
   * @param studentId 受講生ID
   * @return 受講生IDに紐づくコース情報一覧
   */
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentCourse> getCoursesByStudentId(int studentId);

  /**
   * 受講生情報を登録します。 IDは自動採番で設定されます。
   *
   * @param student 受講生情報
   */
  @Insert(
      "INSERT INTO students (full_name, furigana, nickname, email, live_city, age, gender, remark, is_deleted) "
          + "VALUES (#{fullName}, #{furigana}, #{nickname}, #{email}, #{liveCity}, #{age}, #{gender}, #{remark}, false)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);

  /**
   * コース情報を登録します。 IDは自動採番で設定されます。
   *
   * @param course コース情報
   */
  @Insert("INSERT INTO students_courses (student_id, course_name, start_date, end_date) "
      + "VALUES (#{studentId}, #{courseName}, #{startDate}, #{endDate})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerCourse(StudentCourse course);

  /**
   * 受講生情報を更新します。
   *
   * @param student 受講生情報
   */
  @Update(
      "UPDATE students SET full_name = #{fullName}, furigana = #{furigana}, nickname = #{nickname},"
          + " email = #{email}, live_city = #{liveCity}, age = #{age}, gender = #{gender}, "
          + "remark = #{remark}, is_deleted = #{isDeleted} "
          + "WHERE id = #{id}")
  void updateStudent(Student student);

  /**
   * コース情報を更新します。
   *
   * @param course コース情報
   */
  @Update(
      "UPDATE students_courses SET course_name= #{courseName}, start_date = #{startDate}, end_date = #{endDate} "
          + "WHERE id = #{id}")
  void updateCourse(StudentCourse course);

}
