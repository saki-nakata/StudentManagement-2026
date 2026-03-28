package raisetech.StudentManagement2026ver.exception;

import java.time.LocalDateTime;

/**
 * エラー情報を表すオブジェクトです。
 *
 * @param timestamp    エラー発生日時
 * @param path         リクエストパス
 * @param userMessage  ユーザー向けメッセージ
 * @param statusValue  HTTPステータスコード
 * @param statusName   HTTPステータス名
 * @param errorClass   発生した例外クラス名
 * @param errorMessage 例外メッセージ
 */
public record ErrorResponse(LocalDateTime timestamp, String path, String userMessage,
                            int statusValue, String statusName, String errorClass,
                            String errorMessage) {

}
