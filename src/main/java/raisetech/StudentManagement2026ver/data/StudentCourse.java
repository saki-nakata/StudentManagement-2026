package raisetech.StudentManagement2026ver.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * 受講生コース情報を表すオブジェクトです。
 */
@Getter
@Setter
@JsonPropertyOrder({"id", "studentId", "courseName", "startDate", "endDate"})
public class StudentCourse {

  private int id;
  private int studentId;
  private String courseName;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDate;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate endDate;

}
