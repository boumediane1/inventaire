package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDao {
  private Connection connection;

  public ProductDao() {
    connection = CustomMySQLConnection.getConnection();
  }

  public void addProduct(Product product) {
    String sql = "insert into Product (name, category, price, quantity) values (?, ?, ?, ?)";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, product.getName());
      ps.setString(2, product.getCategory());
      ps.setDouble(3, product.getPrice());
      ps.setInt(4, product.getQuantity());
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void updateProduct(Product product) {
    String sql = "update Product set name = ?, category = ?, price = ?, quantity = ? where id = ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, product.getName());
      ps.setString(2, product.getCategory());
      ps.setDouble(3, product.getPrice());
      ps.setInt(4, product.getQuantity());
      ps.setInt(5, product.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteProduct(int id) {
    String sql = "delete from Product where id = ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List<Product> findProductsByName(String name) {
    String sql = "select * from Product where name like concat('%', ?, '%')";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, name);
      return doFindProducts(ps);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return Collections.emptyList();
  }

  public List<Product> findProductsByCategory(String category) {
    String sql = "select * from Product where category like concat('%', ?, '%')";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, category);
      return doFindProducts(ps);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return Collections.emptyList();
  }

  public List<Product> findProductsByQuantityRange(int min, int max) {
    String sql = "select * from Product where quantity between ? and ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setInt(1, min);
      ps.setInt(2, max);
      return doFindProducts(ps);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return Collections.emptyList();
  }

  private List<Product> doFindProducts(PreparedStatement ps) {
    try (ResultSet rs = ps.executeQuery()) {
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
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return Collections.emptyList();
  }
}
