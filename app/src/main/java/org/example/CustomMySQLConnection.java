package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomMySQLConnection {
  private static Connection connection;

  public static Connection getConnection() throws SQLException {
    if (connection == null) {
      String url = "jdbc:mysql://localhost:3306/inventaire";
      String username = "root";
      String password = "";

      connection = DriverManager.getConnection(url, username, password);
    }

    return connection;
  }
}
