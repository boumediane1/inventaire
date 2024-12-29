package inventaire;

import inventaire.rmi.Inventaire;
import inventaire.rmi.InventaireImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Classe principale pour le serveur RMI.
 * Elle configure et enregistre le service RMI Inventaire afin qu'il soit accessible par les clients distants.
 */
public class Serveur {
  public static void main(String[] args) throws RemoteException {
    Inventaire inventaire = new InventaireImpl();
    Registry registry = LocateRegistry.getRegistry();
    registry.rebind("inventaire", inventaire);
  }
}
