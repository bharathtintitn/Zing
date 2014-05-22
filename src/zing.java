import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.QuadCurve2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class zing extends JFrame {
    static ResultSet rs;
    static String[] aa = { "system", "bharath" };
    static String query;

    public static void showFram(String query1) {
        query = query1;
        zing f = new zing();
        f.setTitle("Show Polygons");
        f.setBounds(20, 20, 300, 300);
        f.setVisible(true);
    }

    public zing() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
        Container contentPane = getContentPane();
        contentPane.add(new myPanel());
    }

    static class myPanel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            String url;
            url = "jdbc:oracle:thin:@localhost:1521:xe"; 
            Statement stmt;
            Connection con;

            try { // invoke the oracle thin driver; register it with
                    // DriverManager
                Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            } // this step 'loads' the drivers for Oracle that are 'found'
            catch (Exception e) {
                System.out.println("MR.UnitSitQueries.constructor.Exception: " + e);
            }
            try {
                con = DriverManager.getConnection(url, aa[0], aa[1]); // establish
                // connection to DBMS or database
                stmt = con.createStatement(); // creates object from which SQL
                                                // commands
                // can be sent to the DBMS
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                	
                    String s1 = rs.getString("id");
                    String s2 = rs.getString("name");
                    java.sql.Struct o1 = (java.sql.Struct) rs
                            .getObject("shape");
                    oracle.sql.ARRAY oa3 = (oracle.sql.ARRAY) o1
                            .getAttributes()[3];
                    oracle.sql.ARRAY oa4 = (oracle.sql.ARRAY) o1
                            .getAttributes()[4];
                    String sss = oa3.getBaseTypeName();
                    // System.out.println(sss);
                    // System.out.println(oa3.length());
                    int[] ia3 = oa3.getIntArray();
                    int[] ia4 = oa4.getIntArray();
                    int x[] = new int[20];
                    int y[] = new int[20];

                    int type = ia3[1];
                    int interpretation = ia3[2];
                    // System.out.println("The type is: "+type+" and interpretation: "+interpretation);
                    int k = 0;
                    int l = 0;
                    for (int j = 0; j < ia4.length; j++) {
                        if (j % 2 == 0) {
                            x[k] = ia4[j]; // * 300 / 50;
                            // System.out.print(x[k] + " x ");
                            k++;
                        } else {
                            y[l] = (700 - ia4[j]); // * 300 / 50;
                            // System.out.print(y[l] + " y ");
                            l++;
                        }
                    }

                    System.out.println();
                    System.out.print(s1 + "  ");
                    System.out.println(s2);
                    // System.out.println(o1.getAttributes()[0]);

                    if (type == 1003 && interpretation == 1) {
                        g.drawPolygon(x, y, ia4.length / 2);
                    } else if (type == 1003 && interpretation == 4) {
                        int width = (int)(((x[1] - x[0]) > 0) ? (1.22) * (x[1] - x[0])
                                : (1.22)*(x[0] - x[1]));
                        int hieght = width;
                        int X = x[0];
                        int Y = y[0] - hieght / 2;
                        g.drawOval(X, Y, width, hieght); // x,y,w,h
                        
                    } else if (type == 1003 && interpretation == 3) {
                        int X = x[0];
                        int Y = y[1]; // left top corner
                        int width = ((x[1] - x[0]) > 0) ? x[1] - x[0] : x[0]
                                - x[1];
                        int hieght = ((y[1] - y[0]) > 0) ? y[1] - y[0] : y[0]
                                - y[1];
                        g.drawRect(X, Y, width, hieght); // x,y,w,h
                    } else if (type == 2 && interpretation == 2) {
                                                            
                        QuadCurve2D.Double curve1 = new QuadCurve2D.Double(x[0]+10,y[0],x[1],y[1],x[2],y[2]);
                           ((Graphics2D)g).draw(curve1);
                           
                                                               
                    } else if (type == 2 && interpretation == 1) {
                        g.drawPolyline(x, y, ia4.length / 2);
                    }

                }
                ResultSetMetaData rsmd = rs.getMetaData();
                // rs only has two columns
                int i = rsmd.getColumnCount(); // System.out.println(i);
                int jdbcType = rsmd.getColumnType(2);
                String s1 = rsmd.getColumnLabel(1); // System.out.println(s1);
                // the JDBC type corresponding to ACCESS text has index 12:
                // VARCHAR
                // System.out.println(jdbcType);
                stmt.close();
                con.close();

            } catch (SQLException e) {
                {
                    System.out.println("OOPS" + e.getMessage());
                }

            }
        }
    }
}