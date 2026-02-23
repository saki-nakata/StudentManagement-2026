package raisetech.StudentManagement2026ver;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"id", "studentId", "courseName", "startDate", "endDate"})
public class StudentCourse {

  private int id;
  private int studentId;
  private String courseName;
  private LocalDate startDate;
  private LocalDate endDate;

}
