package controllers;

import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import logic.DatabaseConnection;

public class LoginController {

  @FXML private TextField usernameInput;

  @FXML private PasswordField passwordInput;

  @FXML
  protected void onHelloButtonClick() throws SQLException {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    if (databaseConnection.checkLogin(usernameInput.getText(), passwordInput.getText())) {
      System.out.println("Conexiune a fost realizata");
    } else System.out.println("invalid username/pass");
  }
}
