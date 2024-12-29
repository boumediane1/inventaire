package inventaire.dao;

import inventaire.config.CustomMySQLConnection;
import inventaire.rmi.Produit;

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

  // Ajoute un nouveau produit à la base de données
  public void ajouterProduit(Produit product) throws RemoteException {
    String sql = "insert into Product (name, category, price, quantity) values (?, ?, ?, ?)";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, product.getNom());
      ps.setString(2, product.getCategorie());
      ps.setDouble(3, product.getPrix());
      ps.setInt(4, product.getQuantite());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RemoteException(); // Exception levée pour signaler une erreur RMI
    }
  }

  // Modifie un produit existant dans la base
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

  // Supprime un produit en fonction de son identifiant
  public void supprimerProduit(int id) throws RemoteException {
    String sql = "delete from Product where id = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace(); // Affiche l'erreur, mais ne la relance pas
    }
  }

  // Récupère tous les produits de la base de données
  public List<Produit> listerProduits() throws RemoteException {
    String sql = "select * from Product";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      return doRechercherProduits(ps);
    } catch (SQLException e) {
      throw new RemoteException();
    }
  }

  // Recherche des produits par nom
  public List<Produit> rechercherProduitsParNom(String name) throws RemoteException {
    String sql = "select * from Product where name like concat('%', ?, '%')";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, name);
      return doRechercherProduits(ps);
    } catch (SQLException e) {
      throw new RemoteException();
    }
  }

  // Recherche des produits par catégorie
  public List<Produit> rechercherProduitsParCategorie(String category) throws RemoteException {
    String sql = "select * from Product where category like concat('%', ?, '%')";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, category);
      return doRechercherProduits(ps);
    } catch (SQLException e) {
      throw new RemoteException();
    }
  }

  // Recherche des produits dont la quantité est dans une plage donnée
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

  // Exécute la requête préparée et transforme le résultat en liste de produits
  private List<Produit> doRechercherProduits(PreparedStatement ps) throws RemoteException {
    try (ResultSet rs = ps.executeQuery()) {
      List<Produit> products = new ArrayList<>();
      while (rs.next()) {
        // Crée un objet Produit à partir des colonnes du résultat
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