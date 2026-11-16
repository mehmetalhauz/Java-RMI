import java.rmi.Naming;

public class RmiClient {
    public static void main(String[] args) {
        try {
            // 1. Registry'de adıyla arama (Lookup) -> Bize Koçan (Stub) döner
            RmiServerIntf server = (RmiServerIntf) Naming.lookup("//localhost/RmiServer");

            // İPUCU: İstemcinin elindeki nesnenin gerçek sunucu değil, dinamik Stub (Proxy) olduğunu görelim!
            System.out.println("Elimdeki referansın gerçek sınıfı (Koçan/Stub): " + server.getClass().getName());

            // 2. Uzak Metot Çağrısı (Ağ Şeffaflığı - yerel metot gibi)
            String response = server.getMessage();
            System.out.println("Message received from remote server: " + response);

            // 3. Nesne Serileştirme (Marshalling) ile uzak metot çağrısı
            Mesaj benimMesajim = new Mesaj("Öğrenci", "Dağıtık sistemler harika!");
            String yanit = server.mesajGonder(benimMesajim);
            System.out.println("Sunucu yanıtı: " + yanit);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}