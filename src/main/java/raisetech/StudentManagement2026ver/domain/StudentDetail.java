package raisetech.StudentManagement2026ver.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raisetech.StudentManagement2026ver.data.Student;
import raisetech.StudentManagement2026ver.data.StudentCourse;

/**
 * 受講生情報と紐づくコース情報をまとめた詳細オブジェクトです。
 */
@Schema(description = "受講生情報と紐づくコース情報をまとめた詳細")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"student", "courses"})
public class StudentDetail {

  @Schema(description = "受講生情報")
  @Valid
  private Student student;

  @Schema(description = "コース情報一覧")
  @Valid
  private List<StudentCourse> courses;

}
