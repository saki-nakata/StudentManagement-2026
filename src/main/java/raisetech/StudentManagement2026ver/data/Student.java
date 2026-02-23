package raisetech.StudentManagement2026ver.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"id", "fullName", "nickname", "email", "liveCity", "age", "gender"})
public class Student {

  private int id;
  private String fullName;
  private String furigana;
  private String nickname;
  private String email;
  private String liveCity;
  private int age;
  private String gender;

}
