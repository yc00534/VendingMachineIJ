public class Bank {

    Kasse_Out kasseOut = new Kasse_Out();


    private static boolean CASHBACK = true;

    public static boolean isCASHBACK() {
        return CASHBACK;
    }

    public static boolean getCASHBACK() {
        return CASHBACK;
    }

    public static void setCASHBACK(boolean CASHBACK) {
        Bank.CASHBACK = CASHBACK;
    }

    public Bank (){}

    public void getChange(int cashBack){
        while(cashBack>0){
            Coin coin = getNextCoin(cashBack);
            if(coin == null){
                setCASHBACK(false);
                break;
            }
            cashBack -= coin.getValue();
        }
    }

    private Coin getNextCoin(int cashBack) {
        for(Coin coin : new SortedCoinSet().getSortedCoin()) {
            if(coin.getValue()<=cashBack && kasseOut.getCoinCount(coin.getValue())>0){
                kasseOut.PayOut_AddCoinToBufferList(coin.getValue());
                return coin;
            }
        }
        return null;
    }


}
