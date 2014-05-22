import java.awt.*;
import java.awt.event.*;
import java.awt.geom.QuadCurve2D;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FramRect extends Frame {

    public void draw_figure(Graphics g) {
        try {
            int values[] = new int[50];
            int elem_info[] = new int[50];
            int ordinate[] = new int[50];
            int len, etype, inter, width, height;
            ResultSet res;
            String gtype;
            JDBC jdbc = new JDBC();
            res = jdbc.fetchRows("draw");

            while (res.next()) {

                java.sql.Struct o1 = (java.sql.Struct) res.getObject("SHAPE");
                oracle.sql.ARRAY oa3 = (oracle.sql.ARRAY) o1.getAttributes()[3];
                oracle.sql.ARRAY oa4 = (oracle.sql.ARRAY) o1.getAttributes()[4];
                gtype = o1.getAttributes()[0].toString();
                elem_info = oa3.getIntArray();
                ordinate = oa4.getIntArray();
                len = ordinate.length;

                for (int i = 0; i < len; i++) {
                    values[i] = 5 * ordinate[i] + 50;
                }
                etype = elem_info[1];
                inter = elem_info[2];

                if (etype == 2 && inter == 1) {

                    g.drawLine(values[0], Math.abs(500 - values[1]), values[2], 500 - values[3]);
                }
                if (etype == 2 && inter == 2) {
                    for (int i = 0; i < elem_info.length / 3; i++) {
                        QuadCurve2D.Double arc = new QuadCurve2D.Double(values[6 * i], 500 - values[6 * i + 1],values[6 * i + 2], 500 - values[6 * i + 3],values[6 * i + 4], 500 - values[6 * i + 5]);
                        Graphics2D g2 = (Graphics2D) g;
                        g2.draw(arc);
                    }

                }


                if ("2006".equals(gtype) && inter == 1) {
                    System.out.println("Polyline");
                    for (int i = 0; i < len; i++) {
                        g.drawLine(values[4 * i], Math.abs(500 - values[4 * i + 1]), values[4 * i + 2],Math.abs(500 - values[4 * i + 3]));
                    }
                }

                if (etype == 1003 && inter == 4) {
                    width = 10 + Math.abs(values[0] - values[2]);
                    height = width + 3;
                    g.drawOval(values[0] - 3, 500 - Math.max(Math.max(values[1], values[5]), values[3]), width, height);

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(FramRect.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String args[]) {
        final FramRect f = new FramRect();
        f.enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        f.setTitle("CS615-Final Project-Zing");
        f.setLayout(new FlowLayout());

        MenuBar menubar = new MenuBar();


        /*********** SDO and SDO_GEOM functions ********/
        Menu func_menu = new Menu("SDO and SDO_GEOM Functions");

        final MenuItem relate = new MenuItem("SDO_GEOM.Relate");
        func_menu.add(relate);

        final MenuItem within_dist = new MenuItem("SDO_GEOM.Within_Distance");
        func_menu.add(within_dist);

        final MenuItem intersection = new MenuItem("SDO_Intersection");
        func_menu.add(intersection);

        final MenuItem union = new MenuItem("SDO_Union");
        func_menu.add(union);

        final MenuItem area = new MenuItem("SDO_Area");
        func_menu.add(area);

        final MenuItem length = new MenuItem("SDO_Length");
        func_menu.add(length);

        final MenuItem nn = new MenuItem("SDO_NN");
        func_menu.add(nn);

        final MenuItem mbr = new MenuItem("SDO_MBR");
        func_menu.add(mbr);

        final MenuItem validate = new MenuItem("SDO_Validate Geometry");
        func_menu.add(validate);

        final MenuItem distance = new MenuItem("SDO_Distance");
        func_menu.add(distance);

        /********* Updates to Figure ***********/
        Menu upd_menu = new Menu("Picture Updates");

        final MenuItem insert_1 = new MenuItem("Insert Tail to Monster");
        upd_menu.add(insert_1);

        final MenuItem insert_2 = new MenuItem("Insert Nose to Monster");
        upd_menu.add(insert_2);

        final MenuItem delete_1 = new MenuItem("Delete Nose from Monster");
        upd_menu.add(delete_1);
        //f.setMenuBar(menubar);
        final JDBC jdbcfunction = new JDBC();
        ActionListener lis = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet queryres;
                Object source = e.getSource();
                String id, name, result;

                if (source == relate) {
                    try {
                        queryres = jdbcfunction.fetchRows("relate");
                        while (jdbcfunction.rs.next()) {
                            name = queryres.getString(1);
                            result = queryres.getString(2);
                            System.out.println("Object Name\t\t\tRelationship");
                            System.out.println("-----------------------");
                            System.out.println(name + " " + result);
                            System.out.println("\n\nRelationship of Objects Shown\n\n");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(FramRect.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                if (source == distance) {
                    try {
                        queryres = jdbcfunction.fetchRows("distance");
                        while (jdbcfunction.rs.next()) {
                            result = queryres.getString(1);
                            System.out.println("Distance between human hand and monster head is " + result + "\n\n");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(FramRect.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                if (source == area) {
                    try {
                        queryres = jdbcfunction.fetchRows("area");
                        while (jdbcfunction.rs.next()) {
                            name = queryres.getString(1);
                            result = queryres.getString(2);
                            System.out.println("Object Name\t\t\tArea of Object");
                            System.out.println("-----------------------------------");
                            System.out.println(name + " " + result);
                            System.out.println("\n\nArea of Objects Shown\n\n");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(FramRect.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                if (source == length) {
                    try {
                        queryres = jdbcfunction.fetchRows("length");
                        while (jdbcfunction.rs.next()) {
                            name = queryres.getString(1);
                            result = queryres.getString(2);
                            System.out.println("Object Name\t\t\tLength of Object");
                            System.out.println("-----------------------------------");
                            System.out.println(name + " " + result);
                            System.out.println("\n\nLength of Objects Shown\n\n");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(FramRect.class.getName()).log(Level.SEVERE, null, ex);
                    }


                }

                if (source == intersection) {
                    try {
                        queryres = jdbcfunction.fetchRows("intersection");
                        while (jdbcfunction.rs.next()) {
                            java.sql.Struct o1 = (java.sql.Struct) queryres.getObject(1);
                            String gtype = o1.getAttributes()[0].toString();
                            oracle.sql.ARRAY oa3 = (oracle.sql.ARRAY) o1.getAttributes()[3];
                            oracle.sql.ARRAY oa4 = (oracle.sql.ARRAY) o1.getAttributes()[4];
                            int[] elem_info = oa3.getIntArray();
                            int[] ordinate = oa4.getIntArray();
                            System.out.println("The intersection produces a polygon whose");
                            System.out.println(" GTYPE is: " + gtype);

                            System.out.println(" and elem_info_array is: ");
                            for (int j = 0; j < 3; j++) {
                                System.out.print(elem_info[j] + ",");
                            }
                            System.out.println();
                            System.out.println(" and the ordinate array has is : ");
                            for (int j = 0; j < ordinate.length; j++) {
                                System.out.print(ordinate[j] + ",");
                            }
                            System.out.println();

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(FramRect.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                if (source == nn) {
                    try {
                        queryres = jdbcfunction.fetchRows("nn");
                        System.out.println("Nearest Neighbour to the point (15,20) is ");
                        while (jdbcfunction.rs.next()) {
                            id = queryres.getString(1);
                            name = queryres.getString(2);
                            System.out.println(id + " " + name);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(FramRect.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }


                if (source == within_dist) {
                    try {
                        queryres = jdbcfunction.fetchRows("within_dist");
                        while (jdbcfunction.rs.next()) {
                            result = queryres.getString(1);
                            System.out.println("The part of figure in a distance of 10 from rectangle(1,1,4,4) is ");
                            System.out.println(result);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(FramRect.class.getName()).log(Level.SEVERE, null, ex);

                    }

                }

                if (source == validate) {
                    try {
                        queryres = jdbcfunction.fetchRows("validate");
                        while (jdbcfunction.rs.next()) {
                            String geoname = queryres.getString(1);
                            String bool = queryres.getString(2);
                            System.out.println("Geometry\t\t\tT/F");
                            System.out.println("---------------------");
                            System.out.println(geoname + " " + bool);

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(FramRect.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                if (source == mbr) {
                    try {
                        queryres = jdbcfunction.fetchRows("mbr");
                        while (jdbcfunction.rs.next()) {
                            java.sql.Struct o1 = (java.sql.Struct) queryres.getObject(1);
                            String gtype = o1.getAttributes()[0].toString();
                            oracle.sql.ARRAY oa3 = (oracle.sql.ARRAY) o1.getAttributes()[3];
                            oracle.sql.ARRAY oa4 = (oracle.sql.ARRAY) o1.getAttributes()[4];
                            int[] elem_info = oa3.getIntArray();
                            int[] ordinate = oa4.getIntArray();
                            System.out.println("Minimum Bounding Rectangle has ");
                            System.out.println("GTYPE : " + gtype);

                            System.out.println("and elem_info_array : ");
                            for (int j = 0; j < 3; j++) {
                                System.out.print(elem_info[j] + ",");
                            }
                            System.out.println();
                            System.out.println("and ordinate array : ");
                            for (int j = 0; j < ordinate.length; j++) {
                                System.out.print(ordinate[j] + ",");
                            }
                            System.out.println();

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(FramRect.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                if (source == union) {
                    try {
                        queryres = jdbcfunction.fetchRows("union");
                        while (jdbcfunction.rs.next()) {
                            java.sql.Struct o1 = (java.sql.Struct) queryres.getObject(1);
                            String gtype = o1.getAttributes()[0].toString();
                            oracle.sql.ARRAY oa3 = (oracle.sql.ARRAY) o1.getAttributes()[3];
                            oracle.sql.ARRAY oa4 = (oracle.sql.ARRAY) o1.getAttributes()[4];
                            int[] elem_info = oa3.getIntArray();
                            int[] ordinate = oa4.getIntArray();
                            System.out.println("The union produces a polygon whose");
                            System.out.println("GTYPE is: " + gtype);

                            System.out.println("and elem_info_array is: ");
                            for (int j = 0; j < 3; j++) {
                                System.out.print(elem_info[j] + ",");
                            }
                            System.out.println();
                            System.out.println("and ordinate array is: ");
                            for (int j = 0; j < ordinate.length; j++) {
                                System.out.print(ordinate[j] + ",");
                            }
                            System.out.println();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(FramRect.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }


                if(source==insert_1){
                    queryres=jdbcfunction.fetchRows("insert_1");
                    System.out.println("Monster Tail Added");
                }
                if(source==insert_2){
                    queryres=jdbcfunction.fetchRows("insert_2");
                    System.out.println("Monster Nose Added");
                }
                if(source==delete_1){
                    queryres=jdbcfunction.fetchRows("delete_1");
                    System.out.println("Monster Nose Deleted");
                }



            }
        };

        distance.addActionListener(lis);
        area.addActionListener(lis);
        length.addActionListener(lis);
        nn.addActionListener(lis);
        intersection.addActionListener(lis);
        relate.addActionListener(lis);
        within_dist.addActionListener(lis);
        validate.addActionListener(lis);
        union.addActionListener(lis);
        mbr.addActionListener(lis);
        insert_1.addActionListener(lis);
        insert_2.addActionListener(lis);
        delete_1.addActionListener(lis);
        f.setBounds(0, 0, 600, 600);
        f.setVisible(true);

    }



    public void processWindowEvent(WindowEvent we) {

        if (we.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        } else {
            super.processWindowEvent(we);
        }
    }

}