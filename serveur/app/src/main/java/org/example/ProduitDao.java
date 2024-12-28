package org.example;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDao {
  private Connection connection;

  public ProduitDao() {
    connection = CustomMySQLConnection.getConnection();
  }

  public void ajouterProduit(Produit product) throws RemoteException {
    String sql = "insert into Product (name, category, price, quantity) values (?, ?, ?, ?)";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, product.getNom());
      ps.setString(2, product.getCategorie());
      ps.setDouble(3, product.getPrix());
      ps.setInt(4, product.getQuantite());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RemoteException();
    }
  }

  public void modifierProduit(Produit product) throws RemoteException {
    String sql = "update Product set name = ?, category = ?, price = ?, quantity = ? where id = ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, product.getNom());
      ps.setString(2, product.getCategorie());
      ps.setDouble(3, product.getPrix());
      ps.setInt(4, product.getQuantite());
      ps.setInt(5, product.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RemoteException();
    }
  }

  public void supprimerProduit(int id) throws RemoteException {
    String sql = "delete from Product where id = ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List<Produit> listerProduits() throws RemoteException {
    String sql = "select * from Product";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      return doRechercherProduits(ps);
    } catch (SQLException e) {
      throw new RemoteException();
    }
  }

  public List<Produit> rechercherProduitsParNom(String name) throws RemoteException {
    String sql = "select * from Product where name like concat('%', ?, '%')";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, name);
      return doRechercherProduits(ps);
    } catch (SQLException e) {
      throw new RemoteException();
    }
  }

  public List<Produit> rechercherProduitsParCategorie(String category) throws RemoteException {
    String sql = "select * from Product where category like concat('%', ?, '%')";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, category);
      return doRechercherProduits(ps);
    } catch (SQLException e) {
      throw new RemoteException();
    }
  }

  public List<Produit> rechercherProduitParQuantite(int min, int max) throws RemoteException {
    String sql = "select * from Product where quantity between ? and ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setInt(1, min);
      ps.setInt(2, max);
      return doRechercherProduits(ps);
    } catch (SQLException e) {
      throw new RemoteException();
    }
  }

  private List<Produit> doRechercherProduits(PreparedStatement ps) throws RemoteException {
    try (ResultSet rs = ps.executeQuery()) {
      List<Produit> products = new ArrayList<>();

      while (rs.next()) {
        Produit product =
            new Produit(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getDouble("price"),
                rs.getInt("quantity"));

        products.add(product);
      }

      return products;
    } catch (SQLException e) {
      throw new RemoteException();
    }
  }
}
