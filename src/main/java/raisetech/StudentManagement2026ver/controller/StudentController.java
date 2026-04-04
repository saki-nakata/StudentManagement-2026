package raisetech.StudentManagement2026ver.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement2026ver.domain.StudentDetail;
import raisetech.StudentManagement2026ver.exception.ErrorResponse;
import raisetech.StudentManagement2026ver.exception.TestException;
import raisetech.StudentManagement2026ver.service.StudentService;

/**
 * 受講生情報の検索・登録・更新を行うREST APIのControllerです。
 */
@Tag(name = "受講生詳細", description = "受講生の検索・登録・更新を行うAPI")
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
  @Operation(
      summary = "受講生詳細一覧の取得",
      description = "受講生詳細の一覧を取得します。",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "受講生詳細一覧の取得成功",
              content = @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(
                      schema = @Schema(implementation = StudentDetail.class)
                  )
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "サーバーエラー",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          )
      }
  )
  @GetMapping("/students")
  public List<StudentDetail> getStudentDetails() {
    return service.getStudentDetails();
  }

  /**
   * 指定した受講生IDに紐づく受講生詳細を取得します。
   *
   * @param id 受講生ID
   * @return 受講生詳細
   */
  @Operation(
      summary = "受講生詳細の取得",
      description = "指定した受講生IDに紐づく受講生詳細を取得します。",
      parameters = {
          @Parameter(name = "id", description = "受講生ID", required = true, example = "5")
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "受講生詳細の取得成功",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = StudentDetail.class))
          ),
          @ApiResponse(
              responseCode = "400",
              description = "パラメータの値が不正",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class),
                  examples = @ExampleObject(
                      value = """
                          {
                            "timestamp": "2026-04-01T12:34:56",
                            "path": "/students/10000",
                            "userMessage": "パラメータの値が不正",
                            "statusValue": 400,
                            "statusName": "BAD_REQUEST",
                            "errorClass": "ConstraintViolationException",
                            "errorMessage": "id: 9999以下を入力してください。"
                          }
                          """
                  )
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "指定されたデータは存在しない",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class),
                  examples = @ExampleObject(
                      value = """
                          {
                            "timestamp": "2026-04-01T12:34:56",
                            "path": "/students/123",
                            "userMessage": "指定されたデータは存在しない",
                            "statusValue": 404,
                            "statusName": "NOT_FOUND",
                            "errorClass": "NotFoundException",
                            "errorMessage": "受講生ID に該当するデータは見つかりませんでした。"
                          }
                          """
                  )
              )
          )
      }
  )
  @GetMapping("/students/{id}")
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
  @Operation(summary = "受講生詳細の登録", description = "受講生詳細の新規登録をします。",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "受講生詳細の登録成功",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = StudentDetail.class))
          ),
          @ApiResponse(
              responseCode = "400",
              description = "リクエスト内容が不正",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class),
                  examples = @ExampleObject(
                      value = """
                          {
                            "timestamp": "2026-04-01T12:34:56",
                            "path": "/students",
                            "userMessage": "リクエスト内容が不正",
                            "statusValue": 400,
                            "statusName": "BAD_REQUEST",
                            "errorClass": "IllegalArgumentException",
                            "errorMessage": "登録する受講生情報がありません。"
                          }
                          """
                  )
              )
          ),
          @ApiResponse(
              responseCode = "409",
              description = "データが重複",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class),
                  examples = @ExampleObject(
                      value = """
                          {
                            "timestamp": "2026-04-01T12:34:56",
                            "path": "/students",
                            "userMessage": "データが重複",
                            "statusValue": 409,
                            "statusName": "CONFLICT",
                            "errorClass": "DuplicateException",
                            "errorMessage": "Duplicate data exists"
                          }
                          """
                  )
              )
          )
      }
  )
  @PostMapping("/students")
  public ResponseEntity<StudentDetail> registerStudentDetail(
      @RequestBody @Valid StudentDetail detail) {
    StudentDetail registeredDetail = service.registerStudentDetail(detail);
    return ResponseEntity.status(HttpStatus.CREATED).body(registeredDetail);
  }

  /**
   * 受講生詳細を更新します。論理削除の更新も含みます。
   *
   * @param id     受講生ID
   * @param detail 受講生詳細
   * @return 更新後の受講生詳細
   */
  @Operation(
      summary = "受講生詳細の更新",
      description = "指定した受講生IDに紐づく受講生詳細を更新します。",
      parameters = {
          @Parameter(name = "id", description = "受講生ID", required = true, example = "5")
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "受講生詳細の更新成功",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = StudentDetail.class))
          ),
          @ApiResponse(
              responseCode = "400",
              description = "入力値が不正",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class),
                  examples = {
                      @ExampleObject(
                          value = """
                              {
                                "timestamp": "2026-04-01T12:34:56",
                                "path": "/students/5",
                                "userMessage": "入力値が不正",
                                "statusValue": 400,
                                "statusName": "BAD_REQUEST",
                                "errorClass": "MethodArgumentNotValidException",
                                "errorMessage": "Validation failed"
                              }
                              """
                      )
                  }
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "指定されたデータは存在しない",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class),
                  examples = {
                      @ExampleObject(
                          name = "StudentNotFound",
                          value = """
                              {
                                "timestamp": "2026-04-01T12:34:56",
                                "path": "/students/123",
                                "userMessage": "指定されたデータは存在しない",
                                "statusValue": 404,
                                "statusName": "NOT_FOUND",
                                "errorClass": "NotFoundException",
                                "errorMessage": "受講生ID に該当するデータは見つかりませんでした。"
                              }
                              """
                      ),
                      @ExampleObject(
                          name = "StudentCourseNotFound",
                          value = """
                              {
                                "timestamp": "2026-04-01T12:34:56",
                                "path": "/students/123",
                                "userMessage": "指定されたデータは存在しない",
                                "statusValue": 404,
                                "statusName": "NOT_FOUND",
                                "errorClass": "NotFoundException",
                                "errorMessage": "コースID に該当するデータは見つかりませんでした。"
                              }
                              """
                      )
                  }
              )
          )
      }
  )
  @PatchMapping("/students/{id}")
  public ResponseEntity<StudentDetail> updateStudentDetail(
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
  @Hidden
  @GetMapping("/testException")
  public void testException() throws TestException {
    throw new TestException("意図的に例外を発生。");
  }

}