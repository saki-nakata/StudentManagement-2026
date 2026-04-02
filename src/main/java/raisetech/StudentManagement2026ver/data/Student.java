package raisetech.StudentManagement2026ver.data;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 受講生情報を表すオブジェクトです。
 */
@Schema(description = "受講生情報")
@Getter
@Setter
@JsonPropertyOrder({"id", "fullName", "furigana", "nickname", "email", "liveCity", "age", "gender",
    "remark", "isDeleted"})
public class Student {

  @Schema(description = "受講生ID", minimum = "1", maximum = "9999", accessMode = Schema.AccessMode.READ_ONLY, example = "5")
  private int id;

  @Schema(description = "名前(フルネーム)", requiredMode = REQUIRED, maxLength = 30, example = "Raise Tech")
  @NotBlank(message = "名前(フルネーム)を入力してください。")
  @Size(max = 30, message = "30桁以内で入力してください。")
  private String fullName;

  @Schema(description = "ふりがな", requiredMode = REQUIRED, maxLength = 50, example = "れいず てっく")
  @NotBlank(message = "ふりがなを入力してください。")
  @Size(max = 50, message = "50桁以内で入力してください。")
  @Pattern(regexp = "^[ぁ-んー]+$", message = "ひらがなで入力してください。")
  private String furigana;

  @Schema(description = "ニックネーム", maxLength = 30, example = "テッくん")
  @Size(max = 30, message = "30桁以内で入力してください。")
  private String nickname;

  @Schema(description = "メールアドレス", requiredMode = REQUIRED, maxLength = 50, example = "raise-tech@example.com")
  @NotBlank(message = "メールアドレスを入力してください。")
  @Size(max = 50, message = "50桁以内で入力してください。")
  @Email(message = "有効なメールアドレスを入力してください。")
  private String email;

  @Schema(description = "住んでいる地域(市区町村まで)", maxLength = 50, example = "大阪府大阪市")
  @Size(max = 50, message = "50桁以内で入力してください。")
  private String liveCity;

  @Schema(description = "年齢", requiredMode = REQUIRED, minimum = "18", maximum = "100", example = "38")
  @Min(value = 18, message = "入力可能な数値は18以上です。")
  @Max(value = 100, message = "入力可能な数値は100以下です。")
  private int age;

  @Schema(description = "性別", requiredMode = REQUIRED, example = "その他")
  @NotBlank(message = "性別を入力してください。")
  @Pattern(regexp = "^(男性|女性|その他)$", message = "性別は「男性・女性・その他」のみ入力可能です。")
  private String gender;

  @Schema(description = "備考", maxLength = 150, example = "プログラミング学習")
  @Size(max = 150, message = "150桁以内で入力してください。")
  private String remark;

  @Schema(description = "論理削除フラグ", example = "false")
  private boolean isDeleted;

}
