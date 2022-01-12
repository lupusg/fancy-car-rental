module com.lpsv.fancycarrental {
  requires javafx.controls;
  requires javafx.fxml;
  requires de.jensd.fx.glyphs.fontawesome;
  requires java.sql;
  requires mongo.java.driver;

  opens controllers to javafx.fxml;
  exports controllers;
}