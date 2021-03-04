import java.util.ArrayList;

public class Artikel {
    private String name;
    private int preis;
    private int bestandMin;
    private int bestandMax;
    private int bestandAktuel;


    private int produktId;
    public static int SUMME_DER_ARTIKEL_ARTEN = 0;

    public Artikel(String name, int preis, int bestandMin, int bestandMax, int bestandAktuel, int produktId) {
        this.name = name;
        this.preis = preis;
        this.bestandMin = bestandMin;
        this.bestandMax = bestandMax;
        this.bestandAktuel = bestandAktuel;
        this.produktId = produktId;
        SUMME_DER_ARTIKEL_ARTEN++;
    }


    public int getProduktId() {
        return produktId;
    }

    public String getName() {
        return name;
    }

    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }

    public int getBestandMin() {
        return bestandMin;
    }

    public void setBestandMin(int bestandMin) {
        this.bestandMin = bestandMin;
    }

    public int getBestandMax() {
        return bestandMax;
    }

    public void setBestandMax(int bestandMax) {
        this.bestandMax = bestandMax;
    }

    public int getBestandAktuel() {
        return bestandAktuel;
    }

    public void setBestandAktuel(int bestandAktuel) {
        this.bestandAktuel = bestandAktuel;
    }
}
