package controllers;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logic.DatabaseConnection;
import model.Car;
import org.bson.Document;

public class Item {
  @FXML
  private ImageView img;

  @FXML
  private Label nameLabel;

  @FXML
  private Label priceLabel;


  private Car car;

  public void setData(Car car){
    this.car = car;
    nameLabel.setText(car.getName());
    priceLabel.setText(car.getPrice());
    Image image = new Image(getClass().getResourceAsStream(car.getImgSrc()));
    img.setImage(image);
  }

  @FXML
  void onMouseClick(MouseEvent event) {
    ArrayList<Document> cars = new ArrayList<Document>();
    Document car = new Document();

    car.put("name", this.car.getName());
    car.put("price", this.car.getPrice());
    car.put("imgSrc", this.car.getImgSrc());

    cars.add(car);

    DatabaseConnection.usersCollection.findOneAndUpdate(eq("_id", Login.username), Updates.pushEach("cars", cars));
  }
}
