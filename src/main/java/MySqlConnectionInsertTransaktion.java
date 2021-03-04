import java.sql.*;

public class MySqlConnectionInsertTransaktion {



    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/vendingmaschine?useSSL=false&serverTimezone=UTC";
    private String userName = "root";
    private String userPasswort = "1234";

    long datatimestamp = System.currentTimeMillis();


    public void newTransaction(int coin, int einAus, String time) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, userName, userPasswort);
            Statement stmt = con.createStatement();

            String query = ("INSERT INTO money (wert_cent,menge,datatimestamp)" + " VALUES(?,?,?)");
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, coin);
            preparedStmt.setInt(2, einAus);
            preparedStmt.setString(3, time);
            preparedStmt.execute();
            extracted(stmt);

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void extracted(Statement stmt) throws SQLException {
        ResultSet rs1 = stmt.executeQuery("select * from vendingmaschine.money;");
        while (rs1.next())
            System.out.println(rs1.getInt(1) + "  " + rs1.getString(2) + "  " + rs1.getString(3));
    }

    public MySqlConnectionInsertTransaktion() { }

}
