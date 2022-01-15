package controllers;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logic.DatabaseConnection;
import model.Car;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

public class ItemRemove {
  @FXML private ImageView img;

  @FXML private Label nameLabel;

  @FXML private Label priceLabel;

  private Car car;

  public void setData(Car car) {
    this.car = car;
    nameLabel.setText(car.getName());
    priceLabel.setText(car.getPrice());
    Image image = new Image(getClass().getResourceAsStream(car.getImgSrc()));
    img.setImage(image);
  }

  @FXML
  void onMouseClickRemove(MouseEvent event) throws IOException {
    Document query = DatabaseConnection.usersCollection.find(eq("_id", Login.username)).first();

    JSONObject userJson = new JSONObject(query);
    JSONArray carsArray = userJson.getJSONArray("cars");

    for (int i = 0; i < carsArray.length(); ++i) {
      JSONObject carJson = carsArray.getJSONObject(i);

      if (carJson.get("name").equals(car.getName())) {
        DatabaseConnection.usersCollection.updateOne(eq("_id", Login.username), Updates.set("cars." + i, "0"));
        DatabaseConnection.usersCollection.updateOne(eq("_id", Login.username), Updates.pull("cars", "0"));
        SwitchScene.refreshScene("main/my_cars.fxml", img, 1300, 750);
        break;
      }
    }
  }
}
