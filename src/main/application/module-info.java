module com.lpsv.fancycarrental {
  requires javafx.controls;
  requires javafx.fxml;
  requires de.jensd.fx.glyphs.fontawesome;
  requires mongo.java.driver;
  requires org.json;

  opens controllers to javafx.fxml;
  exports controllers;
}