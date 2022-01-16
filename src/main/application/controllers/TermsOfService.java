package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Car;

/**
 * Controller-ul sectiunii terms of service.
 */

public class TermsOfService {
  private double x, y;

  private List<Car> cars = new ArrayList<>();

  @FXML
  private GridPane myCarsGrid;

  @FXML private Button myCarsBtn;

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
  void toCars() throws IOException {
    SwitchScene.switchScene("main/cars.fxml", myCarsBtn, 1300, 750);
  }

  @FXML
  void toDailyDiscounts() throws IOException {
    SwitchScene.switchScene("main/daily_discounts.fxml", myCarsBtn, 1300, 750);
  }

  @FXML
  void toMyCars() throws IOException {
    SwitchScene.switchScene("main/my_cars.fxml", myCarsBtn, 1300, 750);
  }

  @FXML
  void logOut() throws IOException {
    SwitchScene.switchToLogin("login/login.fxml", myCarsBtn);
  }
}
