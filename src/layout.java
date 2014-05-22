import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class layout extends JFrame {
    JButton query1 = new JButton("select * from zing");    
    JButton query2 = new JButton("parts which nose toches");
    JButton query3 = new JButton("parts inside face");
    JButton query4 = new JButton("parts closest to nose by dist");
    JButton query5 = new JButton("nearest neighbors of nose");
    JButton query6 = new JButton("add your query");
    JTextField text = new JTextField();
    GridLayout Layout = new GridLayout(15,15);
    
    public layout(String name) {
        super(name);
        setResizable(true);
    }
    
    
    public void addComponentsToPane(final Container pane) {
        final JPanel P = new JPanel();
        P.setLayout(Layout);
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(15,15));
         
        //Add controls to set up horizontal and vertical gaps
        controls.add(query1);
        controls.add(query2);
        controls.add(query3);
        controls.add(query4);
        controls.add(query5);
        controls.add(new Label("new query:"));
        controls.add(text);
        controls.add(query6);
        //Process the button press
        query1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            String query= "select * from zing";
                zing.showFram(query);  
            }
        });
        query2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            String query= "SELECT BM.PART_NUM,BM.NAME,BM.SHAPE FROM BIRDMAN BM, BIRDMAN BM2 WHERE BM2.NAME='NOSE' AND BM.NAME<>'NOSE' AND SDO_RELATE(BM.SHAPE,BM2.SHAPE,'MASK=TOUCH')='TRUE'";
                zing.showFram(query);  
            }
        });
        query3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            String query= "SELECT BM.PART_NUM,BM.NAME,BM.SHAPE FROM BIRDMAN BM, BIRDMAN BM2 WHERE BM2.NAME='FACE' AND BM.NAME<>'FACE' AND SDO_RELATE(BM.SHAPE,BM2.SHAPE,'MASK=INSIDE')='TRUE'";
                zing.showFram(query);  
            }
        });
        query4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            String query= "SELECT BM2.PART_NUM,BM2.NAME,BM2.SHAPE FROM BIRDMAN BM, BIRDMAN BM2 WHERE BM.NAME = 'NOSE' AND BM2.NAME<>'NOSE' AND SDO_GEOM.SDO_DISTANCE(BM.SHAPE,BM2.SHAPE,0.5)<0.25 ORDER BY BM2.PART_NUM";
                zing.showFram(query);  
            }
        });
        query5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            String query= "SELECT BM.PART_NUM, BM.NAME, BM.SHAPE FROM BIRDMAN BM, BIRDMAN BM2 WHERE BM2.NAME='NOSE' AND BM.NAME <> 'NOSE'AND SDO_NN(BM.SHAPE,BM2.SHAPE)='TRUE'  AND ROWNUM<=2";
                zing.showFram(query);  
            }
        });
        query6.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                 
                String query= text.getText();
                zing.showFram(query);  
            }
        });
        
             pane.add(controls, BorderLayout.CENTER);
    }
    
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        layout frame = new layout("Query List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
       // frame.setBounds(500,200,500,200);
        //frame.setPreferredSize(new Dimension(400,400));
       
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}