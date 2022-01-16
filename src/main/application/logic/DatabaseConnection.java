package logic;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import model.User;
import org.bson.Document;

public class DatabaseConnection {
  public static MongoClient mongoClient;
  public static MongoDatabase mongoDatabase;
  public static MongoCollection<Document> usersCollection;
  public static MongoCollection<Document> carsCollection;

  public DatabaseConnection() {
    mongoClient = new MongoClient("localhost");

    mongoDatabase = mongoClient.getDatabase("car_rental");

    usersCollection = mongoDatabase.getCollection("users");
    carsCollection = mongoDatabase.getCollection("cars");
  }

  public static boolean checkLogin(User user) throws NoSuchAlgorithmException {
    Document document =
        usersCollection
            .find(and(eq("_id", user.getUsername()), eq("password", SHA.stringToSHA(user.getPassword()))))
            .first();

    return document != null;
  }

  public static boolean signUp(User user) throws NoSuchAlgorithmException {
    if (usersCollection.find(eq("_id", user.getUsername())).first() == null) {
      Document dbUser = new Document();

      dbUser.put("_id", user.getUsername());
      dbUser.put("password", SHA.stringToSHA(user.getPassword()));
      dbUser.put("email", user.getEmail());
      dbUser.put("cars", new ArrayList<Document>());

      usersCollection.insertOne(dbUser);

      return true;
    } else return false;
  }
}
