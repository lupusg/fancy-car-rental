package controllers;

import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import logic.DatabaseConnection;
import model.Car;
import org.bson.Document;
import org.json.JSONObject;

public class Static {
  public static boolean answer = false;
  public static List<Car> discountedCars = getRandomCars();

  /**
   * Itereaza fiecare obiect din array-ul de masini din database si alege la intamplare un numar N
   * de masini care reprezinta 35% din numarul total de masini, al caror pret este redus la
   * jumatate.
   */
  public static List<Car> getRandomCars() {
    int index, initialPrice, discountedPrice;
    ArrayList<Car> cars = new ArrayList<>();
    ArrayList<Car> discountCars = new ArrayList<>();
    ArrayList<Integer> randomCars = new ArrayList<>();
    MongoCursor<Document> cursor = DatabaseConnection.carsCollection.find().iterator();
    Random rand = new Random();
    Car car;

    index = 0;
    while (cursor.hasNext()) {
      JSONObject carJson = new JSONObject(cursor.next().toJson());

      car = new Car();
      car.setName(carJson.get("name").toString());

      initialPrice = Integer.parseInt(carJson.get("price").toString().replaceAll("[\\D]", ""));
      discountedPrice = initialPrice / 2;

      car.setPrice("de la " + discountedPrice + " LEI/zi");
      car.setImgSrc(carJson.get("imgSrc").toString());
      cars.add(car);
      index++;
    }

    for (int i = 0; i < 0.35 * index; ++i) {
      int number = rand.nextInt(index);
      while(randomCars.contains(number)){
        number = rand.nextInt(index);
      }
      randomCars.add(number);
    }

    for(int idx : randomCars){
      discountCars.add(cars.get(idx));
    }

    return discountCars;
  }
}
