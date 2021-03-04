import java.util.ArrayList;
import java.util.Iterator;

public class Kasse_Out {
    public static ArrayList<Integer> LIST, BUFFERED_LIST_OUT, BUFFERED_LIST_IN;
    Iterator<Integer> iterator;

    ArrayList<CoinsContensVendingMaschine> arrayList = new ArrayList<>();

    MySqlConnectionInsertTransaktion db = new MySqlConnectionInsertTransaktion();
    MySqlConnectionGetSummeOfCoins dbInfoMoney = new MySqlConnectionGetSummeOfCoins();

    Time t = new Time();


    private static void initLists() {
        BUFFERED_LIST_IN = new ArrayList<Integer>();
        BUFFERED_LIST_OUT = new ArrayList<Integer>();
        LIST = new ArrayList<Integer>();

        fillArrayListWithCoins();
    }

    private static void fillArrayListWithCoins() {

        MySqlConnectionGetSummeOfCoins dbgsoc = new MySqlConnectionGetSummeOfCoins();
        ArrayList <CoinsContensVendingMaschine> v = new ArrayList<>();
        Iterator iterator;

        v = dbgsoc.getCointsArrayList();


        for (int i = 0; i<v.size(); i++){
            LIST.add(v.get(i).getValue());
        }
    }

    Kasse_Out() {
        initLists();
    }

    public void removeCoinFromList(int CoinValue) {
        for (int i = LIST.size(); i > 0; i--) {
            if (CoinValue == LIST.get(i - 1)) {
                LIST.remove(i - 1);
                i = 0;
            }
        }
    }


    public void PayIn_CancelCoinTransmit() {
        for (int i = BUFFERED_LIST_IN.size(); i > 0; i--) {
            BUFFERED_LIST_IN.remove(i - 1);
        }
    }

    public void PayIn_CoinTransmitFromBufferListToMySQL() {
       for (int i = BUFFERED_LIST_IN.size(); i > 0; i--) {
           db.newTransaction(BUFFERED_LIST_IN.get(i - 1), 1, t.getTime());
           BUFFERED_LIST_IN.remove(i - 1);
       }
    }

    public void PayOut_AddCoinToBufferList(int CoinValue) {
        removeCoinFromList(CoinValue);
        BUFFERED_LIST_OUT.add(CoinValue);
    }

    public void PayOut_CancelCoinTransmit() {
        for (int i = BUFFERED_LIST_OUT.size(); i > 0; i--) {
            LIST.add(BUFFERED_LIST_OUT.get(i - 1));
            BUFFERED_LIST_OUT.remove(i - 1);
        }
    }

    public void PayOut_CoinTransmitFromBufferToUser() {
        for (int i = BUFFERED_LIST_OUT.size(); i > 0; i--) {
            System.out.println("Restgeldauszahlung an Kunde: " + BUFFERED_LIST_OUT.get(i - 1));
           // db.newTransaction(BUFFERED_LIST_OUT.get(i - 1), -1);
            BUFFERED_LIST_OUT.remove(i - 1);
        }
    }

    public int getCoinCount(int CoinValue) {
        int x = 0;
        iterator = LIST.iterator();
        while (iterator.hasNext()) {
            if (CoinValue == iterator.next())
                x++;
        }
        return x;
    }

    public double getSumOfCashPoint() {
        double x = 0;
        iterator = LIST.iterator();
        while (iterator.hasNext()) {
            x += iterator.next();
        }
        return x / 100;
    }


    //UserInterfaceMain ausgabeAnonsieren
    public int getSumFromBufferedListCashBack() {
        int x = 0;
        iterator = BUFFERED_LIST_OUT.iterator();
        while (iterator.hasNext()) {
            x += iterator.next();
        }
        return x;
    }
}


