package controllers;

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
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.Car;

public class Main implements Initializable {
  private double x, y;

  @FXML private GridPane grid;

  @FXML private ScrollPane scroll;

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

  private List<Car> cars = new ArrayList<>();

  private List<Car> getData() {
    List<Car> cars = new ArrayList<>();
    Car car;

    for (int i = 0; i < 5; ++i) {
      car = new Car();
      car.setName("Maybach S60" + i);
      car.setPrice("de la 999 RON/zi");
      car.setImgSrc("/images/mercedes_limo.png");
      cars.add(car);
    }

    return cars;
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    cars.addAll(getData());
    int column = 0;
    int row = 0;

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
        GridPane.setMargin(anchorPane, new Insets(250, 50, 0 , 7));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
