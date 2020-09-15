module com.officina_hide {
	requires java.sql;
	requires javafx.graphics;
	requires javafx.controls;
	
	opens com.officina_hide.fx.view;
	opens com.officina_hide.accounts.fx;
}