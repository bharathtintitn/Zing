/*
 * To change this license header, choose License Headeres in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.QuadCurve2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OracleConnection extends Frame {
	
	static String query,functionType = "";

    public static void zing(String inputQuery, String inputfunctionType) {
	//public static void main(String args[]){
    	query = inputQuery;
    	functionType = inputfunctionType;
        OracleConnection f = new OracleConnection();
        f.enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        f.setTitle("Zing");
        // the firest two params of setBounds set location of upper left corner
        // of the frame, next two are width an height:
        // public void setBounds(int x,int y,int w,int h); 
        f.setBounds(0, 0, 500, 500);
        f.setVisible(true);

    }

    @SuppressWarnings("deprecation")
	@Override
    public void paint(Graphics g) {
    	try{
            int val[] = new int[50];
            int len, etype, inter, width, height, startangle, endangle = 0;
            int count = 0;
            ResultSet res;
            String gtype;
            int ia3[] = new int[50];
            int ia4[] = new int[50];
            ResultSet rs,rs1;
            //JDBC jdbc = new JDBC();
            //res = jdbc.sqldata();
            String url, user, pass;
			user = "system";
			pass = "bharath";
			url = "jdbc:oracle:thin:@localhost:1521:xe";
			Statement stmt;
			Connection con;
			//String query = "select * FROM cola_market ";
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				System.out.println("MR.UnitSitQueries.constructor.Exception: "
					+ e);
			}
			
			try{
				con = DriverManager.getConnection(url, user, pass);
				stmt = con.createStatement(); // creates object from which SQL commands
				// can be sent to the DBMS
				res = stmt.executeQuery(query); //create result object to hold
				//rs1 = stmt.executeQuery(query1);
				while (res.next()) {
					len = 0;
					count = count + 1;
					System.out.print("functionType "+functionType);
					if(functionType.equalsIgnoreCase("zing") || functionType.equalsIgnoreCase("insert") || functionType.equalsIgnoreCase("delete")){
						java.sql.Struct o1 = (java.sql.Struct) res.getObject("shape");
						//int temp = (int) o1.getAttributes()[0];
						oracle.sql.ARRAY oa3 = (oracle.sql.ARRAY) o1.getAttributes()[3];
						oracle.sql.ARRAY oa4 = (oracle.sql.ARRAY) o1.getAttributes()[4];
						gtype = o1.getAttributes()[0].toString();
						ia3 = oa3.getIntArray();
						ia4 = oa4.getIntArray();
						len = ia4.length;
					}else if(functionType.equalsIgnoreCase("intersection")){
						java.sql.Struct o1 = (java.sql.Struct) res.getObject(1);
                        gtype = o1.getAttributes()[0].toString();
                        oracle.sql.ARRAY oa3 = (oracle.sql.ARRAY) o1.getAttributes()[3];
                        oracle.sql.ARRAY oa4 = (oracle.sql.ARRAY) o1.getAttributes()[4];
                        ia3 = oa3.getIntArray();
                        ia4 	= oa4.getIntArray();
                        len = ia4.length;
                        for (int i = 0; i < len; i++) {
    						val[i] =  ia4[i];
    						//val[i] = ia4[i];
    						 System.out.print(val[i] + " ");
    					}
                        continue;
					}else if(functionType.equalsIgnoreCase("distance")){
						System.out.println("in distance");
						int i = res.getInt(1);
						System.out.println("distance is "+i);
						continue;
					}else if(functionType.equalsIgnoreCase("area")){
						System.out.println("in distance");
						String str = res.getString(1);
						int i = res.getInt(2);
						System.out.println("area of "+str+" "+" is "+i);
						continue;
					}else if(functionType.equalsIgnoreCase("difference")){
						System.out.println("in difference");
						
						java.sql.Struct o1 = (java.sql.Struct) res.getObject(1);
						oracle.sql.ARRAY oa3 = (oracle.sql.ARRAY) o1.getAttributes()[3];
						oracle.sql.ARRAY oa4 = (oracle.sql.ARRAY) o1.getAttributes()[4];
						gtype = o1.getAttributes()[0].toString();
						ia3 = oa3.getIntArray();
						ia4 = oa4.getIntArray();
						len = ia4.length;
						for (int i = 0; i < len; i++) {
    						val[i] =  ia4[i];
    						//val[i] = ia4[i];
    						 System.out.print(val[i] + " ");
    					}
						continue;
					}else if(functionType.equalsIgnoreCase("length")){
						System.out.println("in length");
						String str = res.getString(1);
						int i = res.getInt(2);
						System.out.println("length of "+str+" "+" is "+i);
						continue;
					}else if(functionType.equalsIgnoreCase("maxMDR")){
						System.out.println("in mdr");
						//String str = res.getString(1);
						int i = res.getInt(1);
						System.out.println("max MDR of "+i);
						continue;
					}else if(functionType.equalsIgnoreCase("MDR")){
						java.sql.Struct o1 = (java.sql.Struct) res.getObject(1);
                        gtype = o1.getAttributes()[0].toString();
                        oracle.sql.ARRAY oa3 = (oracle.sql.ARRAY) o1.getAttributes()[3];
                        oracle.sql.ARRAY oa4 = (oracle.sql.ARRAY) o1.getAttributes()[4];
                        ia3 = oa3.getIntArray();
                        ia4 	= oa4.getIntArray();
                        len = ia4.length;
                        for (int i = 0; i < len; i++) {
    						val[i] =  ia4[i];
    						//val[i] = ia4[i];
    						 System.out.print(val[i] + " ");
    					}
                        continue;
					}else if(functionType.equalsIgnoreCase("minMDR")){
						System.out.println("in mdr");
						//String str = res.getString(1);
						int i = res.getInt(1);
						System.out.println("min MDR of "+i);
						continue;
					}
					

				   System.out.print("ordinate arrys is "+len);
					for (int i = 0;
							+i < len; i++) {
						val[i] =  ia4[i];
						//val[i] = ia4[i];
						 System.out.print(val[i] + " ");
					}
					etype = ia3[1];
					inter = ia3[2];
					System.out.println("info is"+etype+" "+inter);
			 
					if(count == 2 && functionType.equalsIgnoreCase("delete")){
					
					}else{
					if (etype == 1003 && inter == 4) { // circle
						width = 20 + Math.abs(val[2] - val[0]);
						height = 15 + Math.abs(Math.max(val[1], Math.max(val[3], val[5])) - Math.min(val[1], Math.min(val[3], val[5])));
						g.drawOval(val[0] - 10, 450 - val[5], width+40, width+40);
						//g.drawOval(val[0] - 10, 450 - val[5], width+50, width+50);

					}
					
					if (etype == 2 && inter == 1) { // straight line 
						System.out.println("line "+val[0]+" "+Math.abs(450 - val[1])+" "+ val[2]+" "+Math.abs(450 - val[3]) );
					    g.drawLine(val[0], Math.abs(450 - val[1]), val[2], 450 - val[3]);// working
						//g.drawLine(val[0], Math.abs(450 - val[1]), val[2], 450 - val[3]);				  
					 }
					if (etype == 1003 && inter == 1) { // polygon
	                    int i = 0, j;
	                    int x[] = new int[len / 2];
	                    int y[] = new int[len / 2];
	                    for (j = 0; j < len / 2; j++) {
	                        x[j] = val[2 * i];
	                        //y[j] = Math.abs(450 - val[2 * i + 1]);
	                        y[j] = Math.abs(450 - val[2 * i + 1]);
	                        i++;
	                    }
	                    int number = x.length;
	                    g.drawPolygon(x, y, number);
	                }else if (etype == 2 && inter == 2) {
                        
						QuadCurve2D.Double curve1 = new QuadCurve2D.Double(val[0]+10,val[1],val[2],val[3],val[4],val[5]);
						((Graphics2D)g).draw(curve1);
	                	
					}
					}
				}
				stmt.close();
				con.close();
				
				if(functionType.equalsIgnoreCase("insert")){
					System.out.println("in insert");
					
					g.drawLine(172,300,140,400);// working
					
				}
				
			}catch (SQLException e) {
				System.out.println("OOPS" + e.getMessage());
			}
			
    	}catch(Exception e){
    		//System.out.println("oops "+e.getMessage());
    	}
      
    }
    public void processWindowEvent(WindowEvent we) {

        if (we.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        } else {
            super.processWindowEvent(we);
        }
    }

}
