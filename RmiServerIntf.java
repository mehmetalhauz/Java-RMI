import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiServerIntf extends Remote {
    // Slayttaki temel metot
    String getMessage() throws RemoteException;

    // Bonus: Nesne geçişini (Slayt 21) simüle eden metot
    String mesajGonder(Mesaj mesaj) throws RemoteException;
}