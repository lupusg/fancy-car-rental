package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SwitchScene {
  public static void switchScene(String fxmlFile, Button button, int width, int height) throws IOException {
    Parent root = FXMLLoader.load(SwitchScene.class.getResource(fxmlFile));

    Stage window = (Stage) button.getScene().getWindow();
    window.setScene(new Scene(root, width, height));
  }
}
