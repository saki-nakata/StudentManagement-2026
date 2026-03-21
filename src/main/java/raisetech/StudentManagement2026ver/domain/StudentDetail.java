package raisetech.StudentManagement2026ver.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raisetech.StudentManagement2026ver.data.Student;
import raisetech.StudentManagement2026ver.data.StudentCourse;

/**
 * 受講生情報とその受講生に紐づくコース情報をまとめた詳細オブジェクトです。
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"student", "courses"})
public class StudentDetail {

  @Valid
  private Student student;

  @Valid
  private List<StudentCourse> courses;

}
