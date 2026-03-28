package raisetech.StudentManagement2026ver.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement2026ver.domain.StudentDetail;
import raisetech.StudentManagement2026ver.exception.TestException;
import raisetech.StudentManagement2026ver.service.StudentService;

/**
 * 受講生の検索・登録・更新を行うREST APIのControllerです。
 */
@RestController
@Validated
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * 受講生詳細一覧を取得します。
   *
   * @return 受講生詳細一覧
   */
  @GetMapping("/list")
  public List<StudentDetail> getStudentDetails() {
    return service.getStudentDetails();
  }

  /**
   * 指定した受講生IDに紐づく受講生詳細を取得します。
   *
   * @param id 受講生ID
   * @return 受講生詳細
   */
  @GetMapping("/student/{id}")
  public StudentDetail getStudentDetail(
      @PathVariable("id")
      @Min(value = 1, message = "1以上を入力してください。")
      @Max(value = 9999, message = "9999以下を入力してください。")
      int id) {
    return service.getStudentDetail(id);
  }

  /**
   * 受講生詳細の登録を実行します。
   *
   * @param detail 受講生詳細
   * @return 登録後の受講生詳細
   */
  @PostMapping("register")
  public ResponseEntity<StudentDetail> registerStudentDetail(
      @RequestBody @Valid StudentDetail detail) {
    StudentDetail registeredDetail = service.registerStudentDetail(detail);
    return ResponseEntity.ok(registeredDetail);
  }

  /**
   * 受講生詳細を更新します。論理削除の更新も含みます。
   *
   * @param id     受講生ID
   * @param detail 受講生詳細
   * @return 更新後の受講生詳細
   */
  @PatchMapping("/student/{id}")
  public ResponseEntity<StudentDetail> updateStudent(
      @RequestBody @Valid StudentDetail detail,
      @PathVariable("id") @Min(value = 1, message = "1以上を入力してください。")
      @Max(value = 9999, message = "9999以下を入力してください。") int id) {
    StudentDetail updatedDetail = service.updateStudentDetail(detail, id);
    return ResponseEntity.ok(updatedDetail);
  }

  /**
   * 意図的に例外を発生させ、例外処理の動作確認をします。
   *
   * @throws TestException テスト用の例外
   */
  @GetMapping("/testException")
  public void testException() throws TestException {
    throw new TestException("意図的に例外を発生");
  }

}