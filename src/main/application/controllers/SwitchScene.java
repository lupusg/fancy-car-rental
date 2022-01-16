package controllers;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Clasa care se ocupa de schimbarea scenelor.
 * Metodele au ca referinta un element fxml (buton, etc) de la care obtin Stage-ul pe care se afla
 * stage folosit mai apoi pentru a-i seta o noua scena.
 */
public class SwitchScene {
  public static void switchScene(String fxmlFile, Button button, int width, int height) throws IOException {
    Parent root = FXMLLoader.load(SwitchScene.class.getResource(fxmlFile));

    Stage window = (Stage) button.getScene().getWindow();
    window.setScene(new Scene(root, width, height));
  }

  public static void switchToLogin(String fxmlFile, Button button) throws IOException {
    Parent root = FXMLLoader.load(SwitchScene.class.getResource(fxmlFile));
    Stage window = (Stage) button.getScene().getWindow();
    Scene scene = new Scene(root, 800, 500);

    scene.setFill(Color.TRANSPARENT);
    window.setScene(scene);
  }

  public static void switchToSignUp(String fxmlFile, Button button) throws IOException {
    Parent root = FXMLLoader.load(SwitchScene.class.getResource(fxmlFile));
    Stage window = (Stage) button.getScene().getWindow();
    Scene scene = new Scene(root, 800, 500);

    scene.setFill(Color.TRANSPARENT);
    window.setScene(scene);
  }

  public static void refreshScene(String fxmlFile, ImageView button, int width, int height) throws IOException {
    Parent root = FXMLLoader.load(SwitchScene.class.getResource(fxmlFile));

    Stage window = (Stage) button.getScene().getWindow();
    window.setScene(new Scene(root, width, height));
  }
}
