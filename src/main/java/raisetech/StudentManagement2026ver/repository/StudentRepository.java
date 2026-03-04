package raisetech.StudentManagement2026ver.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentManagement2026ver.data.Student;
import raisetech.StudentManagement2026ver.data.StudentCourse;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> students();

  @Select("SELECT * FROM students_courses")
  List<StudentCourse> courses();

  @Insert(
      "INSERT INTO students (full_name, furigana, nickname, email, live_city, age, gender, remark, is_deleted) "
          + "VALUES (#{fullName}, #{furigana}, #{nickname}, #{email}, #{liveCity}, #{age}, #{gender}, #{remark}, 0)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);

  @Insert("INSERT INTO students_courses (student_id, course_name, start_date, end_date) "
      + "VALUES (#{studentId}, #{courseName}, #{startDate}, #{endDate})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerCourse(StudentCourse course);

}
