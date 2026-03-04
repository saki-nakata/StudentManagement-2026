package raisetech.StudentManagement2026ver.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.StudentManagement2026ver.data.Student;
import raisetech.StudentManagement2026ver.data.StudentCourse;

@Getter
@Setter
@JsonPropertyOrder({"student", "courses"})
public class StudentDetail {

  private Student student;
  private List<StudentCourse> courses;

}
