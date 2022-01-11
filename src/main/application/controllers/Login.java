package controllers;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.DatabaseConnection;

public class Login {

  private double x, y;

  @FXML private TextField usernameInput;

  @FXML private PasswordField passwordInput;

  @FXML private Button loginButton;

  @FXML
  protected void onLoginButtonClick() throws SQLException, IOException {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    SwitchScene switchSceneController = new SwitchScene();

    if (databaseConnection.checkLogin(usernameInput.getText(), passwordInput.getText())) {
      switchSceneController.switchScene("main/main.fxml", loginButton, 1300, 750);
    } else System.out.println("invalid username/pass");
  }

  @FXML
  void dragged(MouseEvent event) {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    stage.setX(event.getScreenX() - x);
    stage.setY(event.getScreenY() - y);
  }

  @FXML
  void close(MouseEvent event) {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();
  }

  @FXML
  void minimize(MouseEvent event) {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setIconified(true);
  }

  @FXML
  void pressed(MouseEvent event) {
    x = event.getSceneX();
    y = event.getSceneY();
  }
}
