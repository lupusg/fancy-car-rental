package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.stage.StageStyle;
import logic.DatabaseConnection;

/**
 * Clasa principala a proiectului.
 */

public class Main extends Application {
  @Override
  public void start(Stage stage) throws IOException {

    DatabaseConnection databaseConnection = new DatabaseConnection();

    Parent root = FXMLLoader.load(getClass().getResource("login/login.fxml"));
    Scene scene = new Scene(root, 800, 500);
    scene.setFill(Color.TRANSPARENT);
    stage.initStyle(StageStyle.TRANSPARENT);
    stage.setResizable(false);
    stage.getIcons().add(new Image(Main.class.getResourceAsStream("login/icon.png")));
    stage.setScene(scene);
    stage.show();
  }
  public static void main(String[] args) {
    launch();
  }
}
