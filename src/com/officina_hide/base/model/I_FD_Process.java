package com.officina_hide.base.model;

/**
 * プロセス情報インターフェース<br>
 * @author officine-hide.com
 * @version 2.11
 * @since 2020/09/17
 */
public interface I_FD_Process {

	/** プロセス情報 : テーブル名 */
	public final String Table_Name = "FD_Process";
	/** プロセス情報 : テーブル論理名 */
	public final String Name = "プロセス情報";
	
	/** プロセス情報ID **/
	public final String COLUMNNAME_FD_PROCESS_ID = Table_Name + "_ID";
	public final String NAME_FD_PROCESS_ID = "プロセス情報ID";
	public final String COMMENT_FD_PROCESS_ID = "プロセス情報を識別するための情報ID";
	/** プロセス名 **/
	public final String COLUMNNAME_PROCESS_NAME = "Process_Name";
	public final String NAME_PROCESS_NAME = "プロセス名";
	public final String COMMENT_PROCESS_NAME = "プロセスを最初に呼びだした画面・処理の名称";
	/** プロセス開始日時 */
	public final String COLUMNNAME_PROCESS_START = "Process_Start";
	public final String NAME_PROCESS_START = "プロセス開始日時";
	public final String COMMENT_PROCESS_START = "プロセスを開始した日時を保管する。";
	/** プロセス終了日時 */
	public final String COLUMNNAME_PROCESS_END = "Process_End";
	public final String NAME_PROCESS_END = "プロセス終了日時";
	public final String COMMENT_PROCESS_END = "プロセスが完了した日時を保管する。";
}
