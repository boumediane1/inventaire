package inventaire;

import inventaire.rmi.Inventaire;
import inventaire.rmi.InventaireImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Serveur {
  public static void main(String[] args) throws RemoteException {
    Inventaire inventaire = new InventaireImpl();
    Registry registry = LocateRegistry.getRegistry();
    registry.rebind("inventaire", inventaire);
  }
}
