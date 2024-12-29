package inventaire.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomMySQLConnection {
  private static Connection connection;

  private CustomMySQLConnection() {}

  public static Connection getConnection() {
    if (connection == null) {
      String url = "jdbc:mysql://localhost:3306/inventaire";
      String username = "root";
      String password = "";

      try {
        connection = DriverManager.getConnection(url, username, password);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return connection;
  }
}
