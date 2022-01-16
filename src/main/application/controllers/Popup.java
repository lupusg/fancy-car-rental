package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
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

  /**
   * Afiseaza fereastra de confirmare si pana nu este inchisa programul principal nu isi continua
   * rularea.
   * @throws IOException
   */

  public void showAndWait() throws IOException {
      Parent root = FXMLLoader.load(Popup.class.getResource("others/popup.fxml"));
      Scene scene = new Scene(root, 290, 206);
      stage = new Stage();

      scene.setFill(Color.TRANSPARENT);
      stage.initStyle(StageStyle.TRANSPARENT);
      stage.setResizable(false);
      stage.setScene(scene);
      stage.showAndWait();
  }
}
