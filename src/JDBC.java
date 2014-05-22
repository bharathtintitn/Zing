
import java.sql.*;

public class JDBC {

    public ResultSet rs,rs1;
    public int ia3[] = new int[50];
    public int ia4[] = new int[50];

    public ResultSet sqldata() {
        String url, user, pass;
        user = "system";
        pass = "bharath";
        url = "jdbc:oracle:thin:@localhost:1521:xe";
        Statement stmt;
        Connection con;
        String query = "select * FROM zing";
       // String query1 = "SELECT SDO_GEOM.SDO_DISTANCE(c_b.shape,c_d.shape,0.005) FROM cola_markets c_b, cola_markets c_d WHERE c_b.name = 'cola_b' AND c_d.name = 'cola_d'";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("MR.UnitSitQueries.constructor.Exception: "
                    + e);
        }
        try {
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.createStatement(); // creates object from which SQL commands
            // can be sent to the DBMS
            rs = stmt.executeQuery(query); //create result object to hold
             //rs1 = stmt.executeQuery(query1);
             
                       
        } catch (SQLException e) {
            System.out.println("OOPS" + e.getMessage());
        }
        return rs;
    }

	public ResultSet fetchRows(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
