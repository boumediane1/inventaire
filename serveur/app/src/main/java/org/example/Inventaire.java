package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Inventaire extends Remote {
  void addProduct(Produit product) throws RemoteException;
  void updateProduct(Produit product) throws RemoteException; 
  void deleteProduct(int id) throws RemoteException; 
  List<Produit> listerProduits() throws RemoteException;
  List<Produit> findProductsByName(String name) throws RemoteException; 
  List<Produit> findProductsByCategory(String name) throws RemoteException; 
  List<Produit> findProductsByQuantityRange(int min, int max) throws RemoteException; 
}
