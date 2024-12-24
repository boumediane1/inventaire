package org.example;

import java.rmi.RemoteException;
import java.util.List;

public class InventaireImpl implements Inventaire {
  private ProduitDao productDao;

  public InventaireImpl() {
    this.productDao = new ProduitDao();
  }

  @Override
  public void addProduct(Produit product) throws RemoteException {
    productDao.ajouterProduit(product);
  }

  @Override
  public void updateProduct(Produit product) throws RemoteException {
    productDao.modifierProduit(product);
  }

  @Override
  public void deleteProduct(int id) throws RemoteException {
    productDao.supprimerProduit(id);
  }

  @Override
  public List<Produit> findProductsByName(String name) throws RemoteException {
    return productDao.rechercherProduitsParNom(name);
  }

  @Override
  public List<Produit> findProductsByCategory(String category) throws RemoteException {
    return productDao.rechercherProduitsParCategorie(category);
  }

  @Override
  public List<Produit> findProductsByQuantityRange(int min, int max) throws RemoteException {
    return productDao.rechercherProduitParQuantite(min, max);
  }
}
