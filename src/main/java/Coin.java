public enum Coin {

    Coin_1_cent(1),
    Coin_2_cent(2),
    Coin_5_cent(5),
    Coin_10_cent(10),
    Coin_20_cent(20),
    Coin_50_cent(50),
    Coin_100_cent(100),
    Coin_200_cent(200);

    private int value;

    Coin(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

}
