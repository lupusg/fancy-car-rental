package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.DatabaseConnection;
import model.User;

public class Login {
  private double x, y;
  public static String username;

  @FXML private TextField usernameInput;

  @FXML private PasswordField passwordInput;

  @FXML private Button loginButton;

  @FXML
  protected void onLoginButtonClick() throws IOException {
    User user = new User(usernameInput.getText(), passwordInput.getText());

    if (DatabaseConnection.checkLogin(user)) {
      username = user.getUsername();
      SwitchScene.switchScene("main/cars.fxml", loginButton, 1300, 750);
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

  @FXML
  void onEnter(KeyEvent event) throws IOException {
    if(event.getCode() == KeyCode.ENTER){
      onLoginButtonClick();
    }
  }
}
