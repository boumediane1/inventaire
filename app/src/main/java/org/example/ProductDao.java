package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

  public List<Product> findProductsByName(String name) throws SQLException {
    String sql = "select * from Product where name like concat('%', ?, '%')";
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setString(1, name);
    return doFindProducts(ps);
  }

  public List<Product> findProductsByCategory(String category) throws SQLException {
    String sql = "select * from Product where category like concat('%', ?, '%')";
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setString(1, category);
    return doFindProducts(ps);
  }

  public List<Product> findProductsByQuantityRange(int min, int max) throws SQLException {
    String sql = "select * from Product where quantity between ? and ?";
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setInt(1, min);
    ps.setInt(2, max);
    return doFindProducts(ps);
  }

  private List<Product> doFindProducts(PreparedStatement ps) throws SQLException {
    ResultSet rs = ps.executeQuery();

    List<Product> products = new ArrayList<>();

    while (rs.next()) {
      Product product =
          new Product(
              rs.getString("name"),
              rs.getString("category"),
              rs.getDouble("price"),
              rs.getInt("quantity"));

      products.add(product);
    }

    return products;
  }
}
