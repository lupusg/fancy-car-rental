package controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.DatabaseConnection;
import model.User;

public class SignUp {
  private double x, y;
  public static String username;

  @FXML private TextField usernameInput, emailInput;

  @FXML private PasswordField passwordInput;

  @FXML private Button signUpButton;

  @FXML private Label errorLabel;

  @FXML
  protected void onSignUpButtonClick() throws IOException, NoSuchAlgorithmException {
    User user = new User(usernameInput.getText(), passwordInput.getText(), emailInput.getText());

    if(DatabaseConnection.signUp(user)){
      SwitchScene.switchToLogin("login/login.fxml", signUpButton);
    } else {
      errorLabel.setText("Username already exists.");
    }
  }

  @FXML
  void toLogIn(MouseEvent event) throws IOException {
    SwitchScene.switchToLogin("login/login.fxml", signUpButton);
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
  void onEnter(KeyEvent event) throws IOException, NoSuchAlgorithmException {
    if(event.getCode() == KeyCode.ENTER){
      onSignUpButtonClick();
    }
  }

}
