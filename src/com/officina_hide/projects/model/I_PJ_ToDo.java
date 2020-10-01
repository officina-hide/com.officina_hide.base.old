package com.officina_hide.projects.model;

import com.officina_hide.base.model.I_FD_Client;

/**
 * ToDo情報インターフェース<br>
 * @author officine-hide.com
 * @version 2.12
 * @since 2020/10/01
 */
public interface I_PJ_ToDo {
	/** ToDo情報 : テーブル名 */
	public final String Table_Name = "PJ_ToDo";
	/** ToDo情報 : テーブル論理名 */
	public final String Name = "ToDo情報";
	
	/** ToDo情報ID */
	public final String COLUMNNAME_PJ_TODO_ID = Table_Name + "_ID";
	public final String NAME_PJ_TODO_ID = "ToDo情報ID";
	public final String COMMENT_PJ_TODO_ID = "ToDo情報を識別する為の情報ID";
	/** クライアント情報ID */
	public final String COLUMNNAME_FD_CLIENT_ID = I_FD_Client.Table_Name + "_ID";
	public final String NAME_FD_CLIENT_ID = "クライアント情報ID";
	public final String COMMENT_FD_CLIENT_ID = "ToDo情報が紐づくクライアントの情報ID";
	/** ToDo件名 */
	public final String COLUMNNAME_TODO_TITLE = "ToDo_Title";
	public final String NAME_TODO_TITLE  = "ToDo件名";
	public final String COMMENT_TODO_TITLE  = "ToDoの内容を表わす件名";

}
