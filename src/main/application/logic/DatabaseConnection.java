package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
  private final Connection connection;
  private final Statement statement;

  public DatabaseConnection() throws SQLException {
    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_rental", "root", "");

    statement = connection.createStatement();
  }

  public boolean checkLogin(String username, String password) throws SQLException {
    final ResultSet result =
        statement.executeQuery(
            "SELECT username, password FROM users WHERE username = '"
                + username
                + "' AND password = '"
                + password
                + "'");
    return result.first();
  }
}
