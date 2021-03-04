import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterfaceMain {
    protected static String ORT;
    private static int TEILMENGE = 0;

    private int userEin, auswahl;
    String name;
    Coin coin;


    Kasse_Out kasseOut = new Kasse_Out();
    Kasse_In kasseIn = new Kasse_In();

    Bank bank = new Bank();
    MySqlConnectionGetSummeOfCoins db = new MySqlConnectionGetSummeOfCoins();



    Artikel art1 = new Artikel("CocaCola",  100, 2, 10, 0, 1);
    Artikel art2 = new Artikel("Fanta",     100, 2, 10, 10,2);
    Artikel art3 = new Artikel("Kaffee",    60,  2, 10, 7, 3);
    Artikel art4 = new Artikel("Tee",       45,  2, 10, 8, 4);
    Artikel art5 = new Artikel("Saft",      80,  1, 10, 9, 5);


    public static int getTEILMENGE() {
        return TEILMENGE;
    }

    public static void setTEILMENGE(int TEILMENGE) {
        UserInterfaceMain.TEILMENGE = TEILMENGE;
    }


    public static void main(String[] args) {
        new UserInterfaceMain("Berlin");
    }

    public UserInterfaceMain(String ort) {
        this.ORT = ort;
        init();
    }

    private void init() {

        Display.Begruessung();
        Display.Auswahl_anbieten(art1.getName(), art1.getPreis(), art1.getBestandAktuel(), art1.getProduktId());
        Display.Auswahl_anbieten(art2.getName(), art2.getPreis(), art2.getBestandAktuel(), art2.getProduktId());
        Display.Auswahl_anbieten(art3.getName(), art3.getPreis(), art3.getBestandAktuel(), art3.getProduktId());
        Display.Auswahl_anbieten(art4.getName(), art4.getPreis(), art4.getBestandAktuel(), art4.getProduktId());
        Display.Auswahl_anbieten(art5.getName(), art5.getPreis(), art5.getBestandAktuel(), art5.getProduktId());

        userEin = 0;
        userEingabe();
    }

    private void userEingabe() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            auswahl = Integer.parseInt(br.readLine());
            if (auswahl > 10 || auswahl < 1) {
                System.out.println("Artikel nicht vorhanden.");
                init();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Falsche Eingabe!");
            init();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (auswahl) {
            case 1:
                if (art1.getBestandAktuel() == 0) {
                    Display.infoAusgabe_keinBestand();
                    init();
                    return;
                }
                Display.CoinEinwurfAnfordern(art1.getName(), art1.getPreis());
                userEinwurf(art1);
                break;

            case 2:
                if(art2.getBestandAktuel()==0){
                    Display.infoAusgabe_keinBestand();
                    init();
                    return;
                }
                Display.CoinEinwurfAnfordern(art2.getName(), art2.getPreis());
                userEinwurf(art2);
                break;

            case 3:
                if(art3.getBestandAktuel()==0){
                    Display.infoAusgabe_keinBestand();
                    init();
                    return;
                }
                Display.CoinEinwurfAnfordern(art3.getName(),art3.getPreis());
                userEinwurf(art3);
                break;

            case 4:
                if(art4.getBestandAktuel()==0){
                    Display.infoAusgabe_keinBestand();
                    init();
                    return;
                }
                Display.CoinEinwurfAnfordern(art4.getName(), art4.getPreis());
                userEinwurf(art4);
                break;

            case 5:
                if(art5.getBestandAktuel()==0){
                    Display.infoAusgabe_keinBestand();
                    init();
                    return;
                }
                Display.CoinEinwurfAnfordern(art5.getName(), art5.getPreis());
                userEinwurf(art5);
                break;

        }
    }

    private void userEinwurf(Artikel art_x) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            userEin = Integer.parseInt(br.readLine());
            if(checkCoinExistens(userEin)==false){
                Display.showText_wrongCoin();
                userEinwurf(art_x);
                return;
            }

            for(Coin coin : Coin.values()){
                if(coin.getValue()==userEin && userEin + getTEILMENGE()==art_x.getPreis()){
                    kasseIn.PayIn_AddCoinToBufferList(coin.getValue());
                    //##
                    ausgabeAnonsieren(art_x);
                    setTEILMENGE(0);

                } else if(coin.getValue()==userEin && (coin.getValue() + getTEILMENGE())>art_x.getPreis()){
                    kasseIn.PayIn_AddCoinToBufferList(coin.getValue());
                    bank.getChange(userEin+getTEILMENGE()-art_x.getPreis());
                    ausgabeAnonsieren(art_x);
                    setTEILMENGE(0);
                } else if (coin.getValue()==userEin && (coin.getValue()+getTEILMENGE()<art_x.getPreis())){
                    System.out.println("bitte noch " + (art_x.getPreis() - getTEILMENGE() - coin.getValue()) + " einwerfen");
                    kasseIn.PayIn_AddCoinToBufferList(coin.getValue());
                    setTEILMENGE(getTEILMENGE()+coin.getValue());
                    userEinwurf(art_x);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean checkCoinExistens(int x) {
        for(Coin coin :Coin.values()) {
            if (coin.getValue() == x)
                return true;
        }
        return false;
    }

    private void ausgabeAnonsieren(Artikel art_x) {
        if(bank.getCASHBACK() == true && kasseOut.getSumFromBufferedListCashBack()==((userEin+getTEILMENGE())-art_x.getPreis())){
         art_x.setBestandAktuel(art_x.getBestandMax()-1);
         Display.DisplayUpdateAusgabe(art_x);
         kasseOut.PayIn_CoinTransmitFromBufferListToMySQL();
         kasseOut.PayOut_CoinTransmitFromBufferToUser();
         setTEILMENGE(0);
         } else{
            System.out.println("Changegeld nicht vorhanden.");
            kasseOut.PayIn_CancelCoinTransmit();
            kasseOut.PayOut_CancelCoinTransmit();
            bank.setCASHBACK(true);
            init();
        }
    }

}

