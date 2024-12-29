package inventaire.rmi;

import inventaire.dao.ProduitDao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Cette classe implémente l'interface Inventaire et fournit les
 * fonctionnalités liées à la gestion des produits dans un inventaire
 * via RMI. Elle sert d'intermédiaire entre le client RMI et le DAO (Data Access Object).
 */

public class InventaireImpl extends UnicastRemoteObject implements Inventaire {
  private ProduitDao productDao;

  public InventaireImpl() throws RemoteException {
    this.productDao = new ProduitDao();
  }

  @Override
  public void ajouterProduit(Produit product) throws RemoteException {
    productDao.ajouterProduit(product);
  }

  @Override
  public void modifierProduit(Produit product) throws RemoteException {
    productDao.modifierProduit(product);
  }

  @Override
  public void supprimerProduit(int id) throws RemoteException {
    productDao.supprimerProduit(id);
  }

  @Override
  public List<Produit> listerProduits() throws RemoteException {
    return productDao.listerProduits();
  }

  @Override
  public List<Produit> rechercherProduitsParNom(String name) throws RemoteException {
    return productDao.rechercherProduitsParNom(name);
  }

  @Override
  public List<Produit> rechercherProduitsParCategorie(String category) throws RemoteException {
    return productDao.rechercherProduitsParCategorie(category);
  }

  @Override
  public List<Produit> rechercherProduitsParQuantite(int min, int max) throws RemoteException {
    return productDao.rechercherProduitParQuantite(min, max);
  }
}
