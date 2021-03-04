public class Display {

    public Display(){}


    public  static void Begruessung(){
        Kasse_Out kasseOut = new Kasse_Out();
        Kasse_INFO kasse_info = new Kasse_INFO();
        System.out.println(" ");
        System.out.println(" + + + + + ");
        System.out.println("Für Sie liegen " + Artikel.SUMME_DER_ARTIKEL_ARTEN + " Getraenke bereit.");
        System.out.println(kasse_info.getSumOfCashPointMySQL());

    }

    public static void Auswahl_anbieten(String name, int Produktpreis, int ProduktRestbestand, int ProduktId ){

        System.out.println("Waehlen Sie eine " + ProduktId+ " fuer " + name + "\t\tPreis: " + Produktpreis
                + " Cent. Es sind noch " + ProduktRestbestand + " Artikel vorhanden.");


    }

    public static void infoAusgabe_keinBestand()
    {
        System.out.println("Kein Bestand. Waehlen Sie ein anderes Artikel");
    }

    public static void DisplayUpdateAusgabe(Artikel x)
    {
        System.out.println("Artikelausgabe: " + x.getName());
    }

    public static void CoinEinwurfAnfordern(String name, int Produktpreis) {
        System.out.println("Sie haben " + name + " fuer " + Produktpreis
                + " Cent gewaehlt. Werfen Sie bitte das Geld ein. Centbetraege als Muenze werden erwartet:");
    }

    public static void showText_wrongCoin() {
        System.out.println("Falsche Muenze. Probiere es nochmal");
    }
}
