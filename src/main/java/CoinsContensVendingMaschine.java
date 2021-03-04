public class CoinsContensVendingMaschine {

    private int value;
    private int ea;


    public CoinsContensVendingMaschine(int value, int ea) {
        this.ea=ea;
        this.value=value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getEa() {
        return ea;
    }

    public void setEa(int ea) {
        this.ea = ea;
    }
}
