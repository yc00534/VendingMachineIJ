import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class MySqlConnectionGetSummeOfCoins {

    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/vendingmaschine?useSSL=false&serverTimezone=UTC";
    private String userName = "root";
    private String userPasswort = "1234";

   static Vector vec;
   static ArrayList arrayList, arrList;


   public ArrayList<CoinsContensVendingMaschine> getCointsArrayList() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, userName, userPasswort);
            Statement stmt = con.createStatement();
            String query = ("SELECT * FROM vendingmaschine.money");
            ResultSet result = stmt.executeQuery(query);


            vec = new Vector();
            arrayList = new ArrayList();

            CoinsContensVendingMaschine cc;

            while (result.next()) {
                cc = new CoinsContensVendingMaschine((Integer.parseInt(result.getString("wert_cent"))),(Integer.parseInt(result.getString("menge"))) );
                vec.add(cc);
                arrayList.add(cc);

            }

            // extracted(stmt);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return arrayList;
    }

    private void extracted(Statement stmt) throws SQLException {
        ResultSet rs1 = stmt.executeQuery("select * from vendingmaschine.money;");
        while (rs1.next())
            System.out.println(rs1.getInt(1) + "  " + rs1.getString(2) + "  " + rs1.getString(3));
    }



    public static void main(String[] args) {
        MySqlConnectionGetSummeOfCoins m = new MySqlConnectionGetSummeOfCoins();

        m.getCointsArrayList();
        double x=0;

/*        for(int i =0; i < vec.size(); i++)
        {

            x+= (((CoinsContensVendingMaschine)vec.elementAt(i)).getEa()*((CoinsContensVendingMaschine)vec.elementAt(i)).getValue());
            System.out.println("Coin: "+((CoinsContensVendingMaschine)vec.elementAt(i)).getValue()+" \t Menge: "+((CoinsContensVendingMaschine)vec.elementAt(i)).getEa());
        }
*/
    }

     public  double getSummeX() {

        double x = 0;
        for(int i =0; i < arrayList.size(); i++)
        {

            x += (((CoinsContensVendingMaschine)arrayList.get(i)).getEa()*((CoinsContensVendingMaschine)arrayList.get(i)).getValue());
            System.out.println("Coin: "+((CoinsContensVendingMaschine)arrayList.get(i)).getValue()+" \t Menge: "+((CoinsContensVendingMaschine)arrayList.get(i)).getEa());
        }

        return  x/100;

    }


}