package controllers;

import com.mongodb.DocumentToDBRefTransformer;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import logic.DatabaseConnection;
import model.Car;
import org.bson.Document;
import org.json.JSONObject;

public class Cars implements Initializable {
  private double x, y;

  @FXML private GridPane grid;

  @FXML private ScrollPane scroll;

  @FXML private Button carsBtn;

  private List<Car> cars = new ArrayList<>();

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
  void toMyCars() throws IOException {
    SwitchScene.switchScene("main/my_cars.fxml", carsBtn, 1300, 750);
  }



  private List<Car> getData() {
    List<Car> cars = new ArrayList<>();
    MongoCursor<Document> cursor = DatabaseConnection.carsCollection.find().iterator();
    Car car;

    while (cursor.hasNext()) {
      JSONObject carJson = new JSONObject(cursor.next().toJson());
      car = new Car();
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

        fxmlLoader.setLocation(getClass().getResource("car/item.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();

        Item itemController = fxmlLoader.getController();
        itemController.setData(cars.get(i));

        if (column == 4) {
          column = 0;
          row++;
        }

        grid.add(anchorPane, column++, row);
        GridPane.setMargin(anchorPane, new Insets(10, 40, 0, 7));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
