package logic;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.User;
import org.bson.Document;

public class DatabaseConnection {
  private MongoClient mongoClient;
  private MongoDatabase mongoDatabase;
  private MongoCollection<Document> collection;

  public DatabaseConnection() {
    mongoClient = new MongoClient("localhost");
    mongoDatabase = mongoClient.getDatabase("car_rental");
    collection = mongoDatabase.getCollection("users");
  }

  public boolean checkLogin(User user) {
    Document document =
        collection
            .find(and(eq("_id", user.getUsername()), eq("password", user.getPassword())))
            .first();

    return document != null;
  }
}
