package raisetech.StudentManagement2026ver.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.StudentManagement2026ver.data.Student;
import raisetech.StudentManagement2026ver.data.StudentCourse;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> students();

  @Select("SELECT * FROM students_courses")
  List<StudentCourse> courses();

  @Select("SELECT * FROM students WHERE id = #{id}")
  Student getStudent(int id);

  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentCourse> getCourse(int studentId);

  @Insert(
      "INSERT INTO students (full_name, furigana, nickname, email, live_city, age, gender, remark, is_deleted) "
          + "VALUES (#{fullName}, #{furigana}, #{nickname}, #{email}, #{liveCity}, #{age}, #{gender}, #{remark}, #{isDeleted})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);

  @Insert("INSERT INTO students_courses (student_id, course_name, start_date, end_date) "
      + "VALUES (#{studentId}, #{courseName}, #{startDate}, #{endDate})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerCourse(StudentCourse course);

  @Update(
      "UPDATE students SET full_name = #{fullName}, furigana = #{furigana}, nickname = #{nickname},"
          + " email = #{email}, live_city = #{liveCity}, age = #{age}, gender = #{gender}, "
          + "remark = #{remark}, is_deleted = #{isDeleted} "
          + "WHERE id = #{id}")
  void updateStudent(Student student);

  @Update(
      "UPDATE students_courses SET course_name= #{courseName}, start_date = #{startDate}, end_date = #{endDate} "
          + "WHERE id = #{id}")
  void updateCourse(StudentCourse course);

}
