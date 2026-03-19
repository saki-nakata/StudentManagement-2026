package raisetech.StudentManagement2026ver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement2026ver.controller.converter.StudentConverter;
import raisetech.StudentManagement2026ver.domain.StudentDetail;
import raisetech.StudentManagement2026ver.service.StudentService;

/**
 * 受講生の検索・登録・更新を行うREST APIのControllerです。
 */
@RestController
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

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
  public StudentDetail getStudentDetail(@PathVariable int id) {
    return service.getStudentDetail(id);
  }

  /**
   * 受講生詳細の登録を実行します。
   *
   * @param detail 受講生詳細
   * @return 実行結果
   */
  @PostMapping("register")
  public ResponseEntity<StudentDetail> registerStudentDetail(@RequestBody StudentDetail detail) {
    service.registerStudentDetail(detail);
    return ResponseEntity.ok(detail);
  }

  /**
   * 受講生詳細を更新します。論理削除の更新も含みます。
   *
   * @param detail 受講生詳細
   * @return 実行結果
   */
  @PutMapping("update")
  public ResponseEntity<StudentDetail> updateStudentDetail(@RequestBody StudentDetail detail) {
    service.updateStudentDetail(detail);
    return ResponseEntity.ok().body(detail);
  }

}
