package raisetech.StudentManagement2026ver.exception;

/**
 * 指定したデータが存在しない場合に発生する例外クラスです。
 */
public class NotFoundException extends RuntimeException {

  public NotFoundException(String message) {
    super(message);
  }

}
