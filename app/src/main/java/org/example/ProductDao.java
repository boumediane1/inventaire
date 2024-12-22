package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDao {
  private Connection connection;

  public ProductDao() throws SQLException {
    connection = CustomMySQLConnection.getConnection();
  }

  public void addProduct(Product product) throws SQLException {
    PreparedStatement ps =
        connection.prepareStatement(
            "insert into Product (name, category, price, quantity) values (?, ?, ?, ?)");
    ps.setString(1, product.getName());
    ps.setString(2, product.getCategory());
    ps.setDouble(3, product.getPrice());
    ps.setInt(4, product.getQuatity());

    ps.executeUpdate();

    connection.close();
  }
}
