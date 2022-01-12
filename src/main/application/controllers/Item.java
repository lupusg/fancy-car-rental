package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Car;

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
}
