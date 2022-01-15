package controllers;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.IllegalCharsetNameException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Popup {
  public Stage stage;

  @FXML private Button noBtn;

  @FXML private Button yesBtn;

  @FXML
  void onNo(ActionEvent event) {
    Static.answer = false;
    Stage stage = (Stage) noBtn.getScene().getWindow();
    stage.close();
  }

  @FXML
  public void onYes(ActionEvent event) {
    Static.answer = true;
    Stage stage = (Stage) yesBtn.getScene().getWindow();
    stage.close();
  }

  public void showAndWait() throws IOException, InterruptedException {
      Parent root = FXMLLoader.load(Popup.class.getResource("others/popup.fxml"));
      Scene scene = new Scene(root, 290, 403);
      stage = new Stage();

      stage.initStyle(StageStyle.TRANSPARENT);
      stage.setResizable(false);
      stage.setScene(scene);
      stage.showAndWait();
  }
}
