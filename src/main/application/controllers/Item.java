package controllers;

import static com.mongodb.client.model.Filters.eq;

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


/**
 * Este controller-ul specific fiecarei masini afisate pe ecran, folosind acelasi fisier FXML
 * care este initializat de mai multe ori si ii sunt schimbate doar informatiile despre fiecare
 * masina. (poza, pret, nume).
 */
public class Item {
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

  /**
   * Metoda care adauga in baza de date o masina, dar nu inainte de a informa utilizatorul printr-un
   * pop in care ii este spus ca trebuie sa ia la cunostinta termenii si conditiile inainte de a
   * semna electronic. (platonic vorbind, doar apasa butonul "semneaza").
   */
  @FXML
  public void onMouseClick() throws IOException {

    Popup popup = new Popup();
    popup.showAndWait();

    if (Static.answer) {
      ArrayList<Document> cars = new ArrayList<Document>();

      Document car = new Document();

      car.put("name", this.car.getName());
      car.put("price", this.car.getPrice());
      car.put("imgSrc", this.car.getImgSrc());

      cars.add(car);

      DatabaseConnection.usersCollection.findOneAndUpdate(
          eq("_id", Login.username), Updates.pushEach("cars", cars));
    }
  }
}
