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
    String sql = "insert into Product (name, category, price, quantity) values (?, ?, ?, ?)";
    PreparedStatement ps = connection.prepareStatement(sql);

    ps.setString(1, product.getName());
    ps.setString(2, product.getCategory());
    ps.setDouble(3, product.getPrice());
    ps.setInt(4, product.getQuantity());

    ps.executeUpdate();

    connection.close();
  }

  public void updateProduct(Product product) throws SQLException {
    String sql = "update Product set name = ?, category = ?, price = ?, quantity = ? where id = ?";
    PreparedStatement ps = connection.prepareStatement(sql);

    ps.setString(1, product.getName());
    ps.setString(2, product.getCategory());
    ps.setDouble(3, product.getPrice());
    ps.setInt(4, product.getQuantity());
    ps.setInt(5, product.getId());

    ps.executeUpdate();

    connection.close();
  }

  public void deleteProduct(int id) throws SQLException {
    String sql = "delete from Product where id = ?";
    PreparedStatement ps = connection.prepareStatement(sql);

    ps.setInt(1, id);

    ps.executeUpdate();

    connection.close();
  }
}
