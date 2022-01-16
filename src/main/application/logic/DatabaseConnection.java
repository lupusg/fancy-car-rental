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

/**
 * Clasa care se ocupa de conexiunea cu baza de date.
 */

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

  /**
   * Verifica daca username-ul si parola introdusa se afla in baza de date.
   * @param user Utilizatorul pentru care se doreste verificarea in baza de date.
   *
   * @return True sau false, daca user-ul a fost gasit sau nu in baza de date.
   */
  public static boolean checkLogin(User user) throws NoSuchAlgorithmException {
    Document document =
        usersCollection
            .find(and(eq("_id", user.getUsername()), eq("password", SHA.stringToSHA(user.getPassword()))))
            .first();

    return document != null;
  }

  /**
   * Intregistreaza un utilizator in baza de date. Parola este criptata cu SHA-256.
   * @param user Utilizatorul pentru care se doreste inregistrarea.
   * @return True sau false, daca actiunea a fost realizata cu succes sau nu.
   */
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
