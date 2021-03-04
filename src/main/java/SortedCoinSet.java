import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortedCoinSet {


    private final static Comparator<Coin> COIN_COMPARATOR = new Comparator<Coin>() {

        @Override
        public int compare(Coin o1, Coin o2) {

            if (o1.getValue() > o2.getValue()) {
                return -1;
            } else if (o1.getValue() < o2.getValue()) {
                return 1;
            } else return 0;
        }
    };

    private SortedSet<Coin> sortedCoin = new TreeSet<>(COIN_COMPARATOR);

    public SortedCoinSet() {
        for (Coin coin : Coin.values()) {
            sortedCoin.add(coin);
        }
    }

    public SortedSet<Coin> getSortedCoin() {
        return sortedCoin;
    }
}
