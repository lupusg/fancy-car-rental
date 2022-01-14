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

  public DatabaseConnection(String database, String collection) {
    mongoClient = new MongoClient("localhost");
    mongoDatabase = mongoClient.getDatabase(database);
    this.collection = mongoDatabase.getCollection(collection);
  }

  public MongoClient getMongoClient() {
    return mongoClient;
  }

  public MongoDatabase getMongoDatabase() {
    return mongoDatabase;
  }

  public MongoCollection<Document> getCollection() {
    return collection;
  }

  public boolean checkLogin(User user) {
    Document document =
        collection
            .find(and(eq("_id", user.getUsername()), eq("password", user.getPassword())))
            .first();

    return document != null;
  }
}
