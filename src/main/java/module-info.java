module com.lpsv.fancycarrental {
  requires javafx.controls;
  requires javafx.fxml;
  requires de.jensd.fx.glyphs.fontawesome;

  opens controllers to javafx.fxml;
  exports controllers;
}