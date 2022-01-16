package controllers;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Car;

public class DailyDiscounts implements Initializable {
  private double x, y;

  private List<Car> cars = new ArrayList<>();

  @FXML private GridPane dailyDiscountsGrid;

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
  void toMyCars() throws IOException {
    SwitchScene.switchScene("main/my_cars.fxml", myCarsBtn, 1300, 750);
  }

  @FXML
  void toTermsOfService() throws IOException {
    SwitchScene.switchScene("main/terms_of_service.fxml", myCarsBtn, 1300, 750);
  }

  @FXML
  void logOut() throws IOException {
    SwitchScene.switchToLogin("login/login.fxml", myCarsBtn);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    cars.addAll(Static.discountedCars);
    int column = 0;
    int row = 1;

    try {
      for (int i = 0; i < cars.size(); ++i) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("car/item.fxml"));

        AnchorPane anchorPane = fxmlLoader.load();

        Item itemController = fxmlLoader.getController();
        itemController.setData(cars.get(i));

        if (column == 4) {
          column = 0;
          row++;
        }

        dailyDiscountsGrid.add(anchorPane, column++, row);
        GridPane.setMargin(anchorPane, new Insets(10, 40, 0, 7));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
