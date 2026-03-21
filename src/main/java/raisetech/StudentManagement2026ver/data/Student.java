package raisetech.StudentManagement2026ver.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@Getter
@Setter
@JsonPropertyOrder({"id", "fullName", "furigana", "nickname", "email", "liveCity", "age", "gender",
    "remark", "isDeleted"})
public class Student {

  private int id;

  @NotBlank(message = "名前(フルネーム)を入力してください。")
  @Size(max = 30, message = "30桁以内で入力してください。")
  private String fullName;

  @NotBlank(message = "ふりがなを入力してください。")
  @Size(max = 50, message = "50桁以内で入力してください。")
  @Pattern(regexp = "^[^ァ-ヶー一-龥]+$", message = "ひらがなで入力してください。")
  private String furigana;

  @Size(max = 30, message = "30桁以内で入力してください。")
  private String nickname;

  @NotBlank(message = "メールアドレスを入力してください。")
  @Size(max = 50, message = "50桁以内で入力してください。")
  @Email(message = "有効なメールアドレスを入力してください。")
  private String email;

  @Size(max = 50, message = "50桁以内で入力してください。")
  private String liveCity;

  @Min(value = 18, message = "入力可能な数値は18以上です。")
  @Max(value = 100, message = "入力可能な数値は100以下です。")
  private int age;

  @NotBlank(message = "性別を入力してください。")
  @Pattern(regexp = "^(男性|女性|その他)$", message = "性別は「男性・女性・その他」のみ入力可能です。")
  private String gender;

  @Size(max = 150, message = "150桁以内で入力してください。")
  private String remark;

  private boolean isDeleted;

}
