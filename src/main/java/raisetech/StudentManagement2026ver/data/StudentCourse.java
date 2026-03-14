package raisetech.StudentManagement2026ver.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@JsonPropertyOrder({"id", "studentId", "courseName", "startDate", "endDate"})
public class StudentCourse {

  private int id;
  private int studentId;
  private String courseName;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate endDate;

}
