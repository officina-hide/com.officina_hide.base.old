package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;

/**
 * パッケージで使用する為の基本設定を行う<br>
 * @author ueno hideo
 * @version 1.20
 * @since 2020/07/13
 * @param args 
 */
public class CreatePackageBase {

	/**
	 * メイン処理<br>
	 * @author ueno hideo
	 * @since 1.20 2020/07/13
	 * @param args
	 */
	public static void main(String[] args) {
		//環境情報設定
		FD_EnvData env = createEnv();
	}

	/**
	 * 環境情報の設定<br>
	 * TODO 本メソッドはパッケージ初期開発時に使用する。将来的には設定用の画面・データベースから取得できるようにする。(2020/07/13 ueno)
	 * @author ueno hideo
	 * @since 1.20 2020/07/13
	 * @return 環境情報
	 */
	private static FD_EnvData createEnv() {
		FD_EnvData env = null;
		return env;
	}

}
