package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {

  @FXML
  private Label welcomeText;

  @FXML
  protected void onHelloButtonClick() {
    System.out.println("merge");
  }
}