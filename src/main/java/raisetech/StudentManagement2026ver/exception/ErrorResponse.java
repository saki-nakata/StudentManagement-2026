package raisetech.StudentManagement2026ver.exception;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "エラー情報")
public record ErrorResponse(
    @Schema(description = "エラー発生日時", format = "date-time", example = "2026-04-01T12:34:56") LocalDateTime timestamp,
    @Schema(description = "リクエストパス", example = "/students") String path,
    @Schema(description = "ユーザー向けメッセージ", example = "サーバーエラー") String userMessage,
    @Schema(description = "HTTPステータスコード", example = "500") int statusValue,
    @Schema(description = "HTTPステータス名", example = "INTERNAL_SERVER_ERROR") String statusName,
    @Schema(description = "例外クラス名", example = "Exception") String errorClass,
    @Schema(description = "例外メッセージ", example = "エラーが発生しました。") String errorMessage) {

}
