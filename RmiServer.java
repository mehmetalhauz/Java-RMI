import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer extends UnicastRemoteObject implements RmiServerIntf {
    public static final String MESSAGE = "Hello World from RMI Server!";

    public RmiServer() throws RemoteException {
        // Slayt 25'teki not: Dinamik stub/koçan oluşturma desteği sağlar
        super(0);
    }

    @Override
    public String getMessage() throws RemoteException {
        System.out.println("[SUNUCU] getMessage() isteği geldi ve yerel olarak çalıştırılıyor...");
        return MESSAGE;
    }

    @Override
    public String mesajGonder(Mesaj mesaj) throws RemoteException {
        System.out.println("[SUNUCU] Gelen Nesne -> Gönderen: " + mesaj.getGonderen()
                + ", İçerik: " + mesaj.getIcerik());
        return "Sunucu mesajını başarıyla aldı, " + mesaj.getGonderen() + "!";
    }

    public static void main(String[] args) {
        try {
            System.out.println("RMI server initializing...");

            // 1. RMI Kayıt Defteri'ni (Registry) başlatma (port 1099)
            try {
                LocateRegistry.createRegistry(1099);
                System.out.println("Java RMI registry created on port 1099.");
            } catch (RemoteException e) {
                System.out.println("Java RMI registry already running.");
            }

            // 2. Uzak nesneyi somutlaştırma ve dışa aktarma (exporting)
            RmiServer server = new RmiServer();

            // 3. Uzak nesneyi Registry'ye bağlama (Binding)
            Naming.rebind("//localhost/RmiServer", server);
            System.out.println("Remote object bound successfully. Server ready.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}