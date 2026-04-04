package raisetech.StudentManagement2026ver.data;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * 受講生コース情報を表すオブジェクトです。
 */
@Schema(description = "受講生コース情報")
@Getter
@Setter
@JsonPropertyOrder({"id", "studentId", "courseName", "startDate", "endDate"})
public class StudentCourse {

  @Schema(description = "コースID", minimum = "1", maximum = "9999", accessMode = Schema.AccessMode.READ_ONLY, example = "7")
  private int id;

  @Schema(description = "受講生ID", example = "5")
  @Min(value = 1, message = "1以上を入力してください。")
  @Max(value = 9999, message = "9999以下を入力してください。")
  private int studentId;

  @Schema(description = "コース名", requiredMode = REQUIRED, example = "フロントエンド")
  @NotBlank(message = "コース名を入力してください。")
  @Pattern(
      regexp = "^(Java|フロントエンド|WordPress|Webマーケティング|AWS)$",
      message = "「Java・AWS・フロントエンド・WordPress・Webマーケティング」のみ入力可能です。"
  )
  private String courseName;

  @Schema(description = "受講開始日", format = "date", example = "2026-01-01")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDate;

  @Schema(description = "受講終了予定日", format = "date", example = "2026-12-31")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate endDate;

}
