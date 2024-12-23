package org.example;

import java.rmi.RemoteException;
import java.util.List;

public class InventoryImpl implements Inventory {
  private ProductDao productDao;

  public InventoryImpl() {
    this.productDao = new ProductDao();
  }

  @Override
  public void addProduct(Product product) throws RemoteException {
    productDao.addProduct(product);
  }

  @Override
  public void updateProduct(Product product) throws RemoteException {
    productDao.updateProduct(product);
  }

  @Override
  public void deleteProduct(int id) throws RemoteException {
    productDao.deleteProduct(id);
  }

  @Override
  public List<Product> findProductsByName(String name) throws RemoteException {
    return productDao.findProductsByName(name);
  }

  @Override
  public List<Product> findProductsByCategory(String category) throws RemoteException {
    return productDao.findProductsByCategory(category);
  }

  @Override
  public List<Product> findProductsByQuantityRange(int min, int max) throws RemoteException {
    return productDao.findProductsByQuantityRange(min, max);
  }
}
