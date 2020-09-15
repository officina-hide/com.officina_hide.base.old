package com.officina_hide.base.model;

/**
 * DBログ情報インターフェース<br>
 * @author officine-hide.com
 * @version 2.11
 * @since 2020/09/15
 */
public interface I_FD_Log {

	/** DBログ情報 : テーブル名 */
	public final String Table_Name = "FD_Log";
	/** DBログ情報 : テーブル論理名 */
	public final String Name = "ログ情報";
	
	/** DBログ情報ID */
	public final String CLUMNNAME_FD_LOG_ID = Table_Name + "_ID";
	public final String  NAME_FD_LOG_ID  = "ログ情報ID";
	public final String COMMENT_FD_LOG_ID = "ログ情報を識別するための情報ID";
	/** ログプロセスID */
	public final String CLUMNNAME_LOG_PROCESS_ID = "Log_Process_ID";
	public final String  NAME_LOG_PROCESS_ID = "ログプロセスID";
	public final String COMMENT_LOG_PROCESS_ID = "ログの発生単位";
	/** メッセージタイプコード */
	public final String CLUMNNAME_MESSAGE_TYPE_CODE = "Message_Type_Code";
	public final String  NAME_MESSAGE_TYPE_CODE = "メッセージタイプコード";
	public final String COMMENT_MESSAGE_TYPE_CODE = "メッセージの種別を表すコード";
	/** メッセージモードコード */
	public final String CLUMNNAME_MESSAGE_MODE_CODE = "Message_Mode_Code";
	public final String  NAME_MESSAGE_MODE_CODE = "メッセージモードコード ";
	public final String COMMENT_MESSAGE_MODE_CODE = "メッセージの表示モードを表すコード";
	/** メッセージテキスト */
	public final String CLUMNNAME_MESSAGE_TEXT = "Message_Text";
	public final String  NAME_MESSAGE_TEXT = "メッセージ文字列";
	public final String COMMENT_MESSAGE_TEXT = "メッセージとして表示される文字列";
	
	/** メッセージタイプ : 情報表示 */
	public final int MESSAGE_TYPE_INFORMATION = 1;
	/** メッセージタイプ : エラー */
	public final int MESSAGE_TYPE_ERROR = 2;
	/** メッセージタイプ : DB処理 */
	public final int MESSAGE_TYPE_DB = 3;
	
	/** メッセージモード : ノーマル */
	public final int MESSAGE_MODE_NORMAL = 1;
	/** メッセージモード : ノーマル */
	public final int MESSAGE_MODE_DEBUG = 2;
}
