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
import logic.DatabaseConnection;
import model.Car;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

public class MyCars implements Initializable {
  private double x, y;

  private List<Car> cars = new ArrayList<>();

  @FXML private GridPane myCarsGrid;

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
  void toTermsOfService() throws IOException {
    SwitchScene.switchScene("main/terms_of_service.fxml", myCarsBtn, 1300, 750);
  }

  @FXML
  void logOut() throws IOException {
    SwitchScene.switchToLogin("login/login.fxml", myCarsBtn);
  }

  private List<Car> getData() {
    List<Car> cars = new ArrayList<>();

    Document user = DatabaseConnection.usersCollection.find(eq("_id", Login.username)).first();

    JSONObject userJson = new JSONObject(user);
    JSONArray carsArray = userJson.getJSONArray("cars");

    for (int i = 0; i < carsArray.length(); ++i) {
      JSONObject carJson = carsArray.getJSONObject(i);
      Car car = new Car();

      car.setName(carJson.get("name").toString());
      car.setPrice(carJson.get("price").toString());
      car.setImgSrc(carJson.get("imgSrc").toString());

      cars.add(car);
    }
    return cars;
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    cars.addAll(getData());
    int column = 0;
    int row = 1;

    try {
      for (int i = 0; i < cars.size(); ++i) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("car/item_remove.fxml"));

        AnchorPane anchorPane = fxmlLoader.load();

        ItemRemove itemController = fxmlLoader.getController();
        itemController.setData(cars.get(i));

        if (column == 4) {
          column = 0;
          row++;
        }

        myCarsGrid.add(anchorPane, column++, row);
        GridPane.setMargin(anchorPane, new Insets(10, 40, 0, 7));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
