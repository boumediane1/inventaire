package inventaire.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomMySQLConnection {
  private static Connection connection;

  // Constructeur privé pour empêcher l'instanciation directe de cette classe
  private CustomMySQLConnection() {}

  public static Connection getConnection() {
    if (connection == null) {
      String url = "jdbc:mysql://localhost:3306/inventaire";
      String username = "root";
      String password = "";

      try {
        connection = DriverManager.getConnection(url, username, password); // Crée une connexion unique à la base
      } catch (SQLException e) {
        e.printStackTrace(); // Affiche les erreurs de connexion
      }
    }

    return connection;
  }
}
