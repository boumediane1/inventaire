package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProduitDao {
  private Connection connection;

  public ProduitDao() {
    connection = CustomMySQLConnection.getConnection();
  }

  public void ajouterProduit(Produit product) {
    String sql = "insert into Product (name, category, price, quantity) values (?, ?, ?, ?)";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, product.getNom());
      ps.setString(2, product.getCategorie());
      ps.setDouble(3, product.getPrix());
      ps.setInt(4, product.getQuantite());
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void modifierProduit(Produit product) {
    String sql = "update Product set name = ?, category = ?, price = ?, quantity = ? where id = ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, product.getNom());
      ps.setString(2, product.getCategorie());
      ps.setDouble(3, product.getPrix());
      ps.setInt(4, product.getQuantite());
      ps.setInt(5, product.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void supprimerProduit(int id) {
    String sql = "delete from Product where id = ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List<Produit> rechercherProduitsParNom(String name) {
    String sql = "select * from Product where name like concat('%', ?, '%')";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, name);
      return doRechercherProduits(ps);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return Collections.emptyList();
  }

  public List<Produit> rechercherProduitsParCategorie(String category) {
    String sql = "select * from Product where category like concat('%', ?, '%')";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, category);
      return doRechercherProduits(ps);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return Collections.emptyList();
  }

  public List<Produit> rechercherProduitParQuantite(int min, int max) {
    String sql = "select * from Product where quantity between ? and ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setInt(1, min);
      ps.setInt(2, max);
      return doRechercherProduits(ps);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return Collections.emptyList();
  }

  private List<Produit> doRechercherProduits(PreparedStatement ps) {
    try (ResultSet rs = ps.executeQuery()) {
      List<Produit> products = new ArrayList<>();

      while (rs.next()) {
        Produit product =
            new Produit(
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
