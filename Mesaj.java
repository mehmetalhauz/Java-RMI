import java.io.Serializable;

// Ağ üzerinden taşınabilmesi (Marshalling) için Serializable olmalı
public class Mesaj implements Serializable {
    private String gonderen;
    private String icerik;

    public Mesaj(String gonderen, String icerik) {
        this.gonderen = gonderen;
        this.icerik = icerik;
    }

    public String getGonderen() {
        return gonderen;
    }

    public String getIcerik() {
        return icerik;
    }
}