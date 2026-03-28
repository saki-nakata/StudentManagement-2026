package raisetech.StudentManagement2026ver.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Arrays;
import javax.naming.ServiceUnavailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * アプリケーション全体で発生する例外を処理し、エラー情報を返却するためのクラスです。
 * <p>
 * 例外の種類に応じて適切なHTTPステータスコードとエラーメッセージを設定します。
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  /**
   * リクエストボディのバリデーションエラー発生時に呼び出される例外処理です。
   *
   * @param ex      発生したバリデーション例外
   * @param request HTTPリクエスト情報
   * @return HTTPステータス400（Bad Request）とエラー情報
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException
  (MethodArgumentNotValidException ex, HttpServletRequest request) {
    log.error("MethodArgumentNotValidException: {}", ex.getMessage(), ex);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ErrorResponse error = buildErrorMessage(status, "バリデーションエラー", ex, request);
    return ResponseEntity.status(status).body(error);
  }

  /**
   * リクエストパラメータのバリデーションエラー発生時に呼び出される例外処理です。
   *
   * @param ex      発生したバリデーション例外
   * @param request HTTPリクエスト情報
   * @return HTTPステータス400（Bad Request）とエラー情報
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> handleConstraintViolationException
  (ConstraintViolationException ex, HttpServletRequest request) {
    log.error("ConstraintViolationException: {}", ex.getMessage(), ex);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ErrorResponse error = buildErrorMessage(status, "パラメータが不正", ex, request);
    return ResponseEntity.status(status).body(error);
  }

  /**
   * リクエストパラメータの型不一致エラー発生時に呼び出される例外処理です。
   *
   * @param ex      発生した型不一致の例外
   * @param request HTTPリクエスト情報
   * @return HTTPステータス400（Bad Request）とエラー情報
   */
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
    log.error("MethodArgumentTypeMismatchException", ex);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ErrorResponse error = buildErrorMessage(status, "パラメータの型が不正", ex, request);
    return ResponseEntity.status(status).body(error);
  }

  /**
   * 必須のリクエストパラメータが不足している場合に呼び出される例外処理です。
   *
   * @param ex      発生したパラメータ不足の例外
   * @param request HTTPリクエスト情報
   * @return HTTPステータス400（Bad Request）とエラー情報
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
      MissingServletRequestParameterException ex, HttpServletRequest request) {
    log.error("MissingServletRequestParameterException: {}", ex.getMessage(), ex);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ErrorResponse error = buildErrorMessage(status, "必須パラメータが不足", ex, request);
    return ResponseEntity.status(status).body(error);
  }

  /**
   * リクエストボディの形式が不正な場合に呼び出される例外処理です。
   *
   * @param ex      発生したリクエスト読み取りエラーの例外
   * @param request HTTPリクエスト情報
   * @return HTTPステータス400（Bad Request）とエラー情報
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException ex, HttpServletRequest request) {
    log.error("HttpMessageNotReadableException: {}", ex.getMessage(), ex);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ErrorResponse error = buildErrorMessage(status, "リクエスト形式が不正", ex, request);
    return ResponseEntity.status(status).body(error);
  }

  /**
   * 不正な引数が指定された場合に呼び出される例外処理です。
   *
   * @param ex      発生した引数不正の例外
   * @param request HTTPリクエスト情報
   * @return HTTPステータス400（Bad Request）とエラー情報
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
      IllegalArgumentException ex, HttpServletRequest request) {
    log.error("IllegalArgumentException: {}", ex.getMessage(), ex);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ErrorResponse error = buildErrorMessage(status, "引数が不正", ex, request);
    return ResponseEntity.status(status).body(error);
  }

  /**
   * アクセス権限がない場合に呼び出される例外処理です。
   *
   * @param ex      発生したアクセス拒否の例外
   * @param request HTTPリクエスト情報
   * @return HTTPステータス403（Forbidden）とエラー情報
   */
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorResponse> handleAccessDeniedException(
      AccessDeniedException ex, HttpServletRequest request) {
    log.error("AccessDeniedException: {}", ex.getMessage(), ex);
    HttpStatus status = HttpStatus.FORBIDDEN;
    ErrorResponse error = buildErrorMessage(status, "アクセス権限がない", ex, request);
    return ResponseEntity.status(status).body(error);
  }

  /**
   * 存在しないURLが指定された場合に呼び出される例外処理です。
   *
   * @param ex      発生したハンドラ未検出の例外
   * @param request HTTPリクエスト情報
   * @return HTTPステータス404（Not Found）とエラー情報
   */
  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(
      NoHandlerFoundException ex, HttpServletRequest request) {
    log.error("NoHandlerFoundException", ex);
    HttpStatus status = HttpStatus.NOT_FOUND;
    ErrorResponse error = buildErrorMessage(status, "指定されたURLは存在しない", ex, request);
    return ResponseEntity.status(status).body(error);
  }

  /**
   * 指定したデータが存在しない場合に呼び出される例外処理です。
   *
   * @param ex      発生したリソース未検出の例外
   * @param request HTTPリクエスト情報
   * @return HTTPステータス404（Not Found）とエラー情報
   */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFoundException(
      NotFoundException ex, HttpServletRequest request) {
    log.error("NotFoundException", ex);
    HttpStatus status = HttpStatus.NOT_FOUND;
    ErrorResponse error = buildErrorMessage(status, "指定されたデータは存在しない", ex, request);
    return ResponseEntity.status(status).body(error);
  }

  /**
   * サポートされていないHTTPメソッドが指定された場合に呼び出される例外処理です。
   *
   * @param ex      発生したHTTPメソッド不正の例外
   * @param request HTTPリクエスト情報
   * @return HTTPステータス405（Method Not Allowed）とエラー情報
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
    log.error("HttpRequestMethodNotSupportedException: {}", ex.getMessage(), ex);
    HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
    ErrorResponse error = buildErrorMessage(status, "HTTPメソッドが不正", ex, request);
    return ResponseEntity.status(status).body(error);
  }

  /**
   * データの重複によるエラーが発生した場合に呼び出される例外処理です。
   *
   * @param ex      発生した重複データの例外
   * @param request HTTPリクエスト情報
   * @return HTTPステータス409（Conflict）とエラー情報
   */
  @ExceptionHandler(DuplicateKeyException.class)
  public ResponseEntity<ErrorResponse> handleDuplicateKeyException
  (DuplicateKeyException ex, HttpServletRequest request) {
    log.error("DuplicateKeyException: {}", ex.getMessage(), ex);
    HttpStatus status = HttpStatus.CONFLICT;
    ErrorResponse error = buildErrorMessage(status, "データが重複", ex, request);
    return ResponseEntity.status(status).body(error);
  }

  /**
   * リクエストのContent-Typeが不正な場合に呼び出される例外処理です。
   *
   * @param ex      発生したメディアタイプ不正の例外
   * @param request HTTPリクエスト情報
   * @return HTTPステータス415（Unsupported Media Type）とエラー情報
   */
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(
      HttpMediaTypeNotSupportedException ex, HttpServletRequest request) {
    log.error("HttpMediaTypeNotSupportedException: {}", ex.getMessage(), ex);
    HttpStatus status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
    ErrorResponse error = buildErrorMessage(status, "Content-Typeが不正", ex, request);
    return ResponseEntity.status(status).body(error);
  }

  /**
   * サービスが一時的に利用できない場合に呼び出される例外処理です。
   *
   * @param ex      発生したサービス利用不可の例外
   * @param request HTTPリクエスト情報
   * @return HTTPステータス503（Service Unavailable）とエラー情報
   */
  @ExceptionHandler(ServiceUnavailableException.class)
  public ResponseEntity<ErrorResponse> handleServiceUnavailableException(
      ServiceUnavailableException ex, HttpServletRequest request) {
    log.error("ServiceUnavailableException.class: {}", ex.getMessage(), ex);
    HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
    ErrorResponse error = buildErrorMessage(status, "サービスを利用できない", ex, request);
    return ResponseEntity.status(status).body(error);
  }

  /**
   * テスト用の例外が発生した場合に呼び出される例外処理
   *
   * @param ex      発生したテスト用の例外
   * @param request HTTPリクエスト情報
   * @return HTTPステータス400（Bad Request）とエラー情報
   */
  @ExceptionHandler(TestException.class)
  public ResponseEntity<ErrorResponse> handleTestException
  (TestException ex, HttpServletRequest request) {
    log.error("TestException: {}", ex.getMessage(), ex);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ErrorResponse error = buildErrorMessage(status, "テスト例外", ex, request);
    return ResponseEntity.status(status).body(error);
  }

  /**
   * 予期しない例外が発生した場合に呼び出される例外処理です。
   *
   * @param ex      発生した例外
   * @param request HTTPリクエスト情報
   * @return HTTPステータス500（Internal Server Error）とエラー情報
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(
      Exception ex, HttpServletRequest request) {
    log.error("Exception: {}", ex.getMessage(), ex);
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    ErrorResponse error = buildErrorMessage(status, "サーバーエラー", ex, request);
    return ResponseEntity.status(status).body(error);
  }

  /**
   * 例外情報とリクエスト情報を元にエラー情報を生成します。
   *
   * @param status      HTTPステータス
   * @param userMessage ユーザー向けメッセージ
   * @param ex          発生した例外
   * @param request     HTTPリクエスト情報
   * @return エラー情報を含む結果オブジェクト
   */
  private ErrorResponse buildErrorMessage(
      HttpStatus status,
      String userMessage,
      Exception ex,
      HttpServletRequest request
  ) {
    String simpleErrorMessage = simplifyErrorMessage(ex.getMessage());
    return new ErrorResponse(
        LocalDateTime.now(),
        request.getRequestURI(),
        userMessage,
        status.value(),
        status.name(),
        ex.getClass().getSimpleName(),
        simpleErrorMessage
    );
  }

  /**
   * 例外メッセージを簡潔に整形します。
   * <p>
   * 複数行のメッセージの場合は空行をスキップし、最初の有効な行を返します。 メッセージが null または空の場合はデフォルトのエラーメッセージを返します。
   *
   * @param errorMessage 例外メッセージ
   * @return 簡潔なエラーメッセージ
   */
  private String simplifyErrorMessage(String errorMessage) {
    String defaultMessage = "エラーが発生しました。";
    if (errorMessage == null || errorMessage.isBlank()) {
      errorMessage = defaultMessage;
    } else {
      String[] lines = errorMessage.split("\\r?\\n");
      errorMessage = Arrays.stream(lines)
          .filter(line -> !line.isBlank())
          .findFirst()
          .orElse(defaultMessage);
    }
    return errorMessage;
  }

}
