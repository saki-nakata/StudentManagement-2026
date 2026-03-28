package raisetech.StudentManagement2026ver.exception;

/**
 * テスト用に意図的に発生させる例外クラスです。
 * <p>
 * 例外処理の動作確認に使用します。
 */
public class TestException extends Exception {

  public TestException() {
    super();
  }

  public TestException(String message) {
    super(message);
  }

  public TestException(String message, Throwable cause) {
    super(message, cause);
  }

  public TestException(Throwable cause) {
    super(cause);
  }

}
