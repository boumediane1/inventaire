package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface Inventory extends Remote {
  void addProduct(Product product) throws RemoteException; 
  void updateProduct(Product product) throws RemoteException; 
  void deleteProduct(int id) throws RemoteException; 
  List<Product> findProductsByName(String name) throws RemoteException; 
  List<Product> findProductsByCategory(String name) throws RemoteException; 
  List<Product> findProductsByQuantityRange(int min, int max) throws RemoteException; 
}
