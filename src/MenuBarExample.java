import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class MenuBarExample extends JPanel {

  public JMenuBar menuBar;

  public JToolBar toolBar;

  public MenuBarExample() {
    super(true);

    // Create a menu bar and give it a bevel border.
    menuBar = new JMenuBar();
    menuBar.setBorder(new BevelBorder(BevelBorder.RAISED));

    // Create a menu and add it to the menu bar.
    JMenu menu = new JMenu("Menu");
    menuBar.add(menu);

    // Create a toolbar and give it an etched border.
    toolBar = new JToolBar();
    toolBar.setBorder(new EtchedBorder());

    // Instantiate a sample action with the NAME property of
    // "Download" and the appropriate SMALL_ICON property.
    SampleAction exampleAction = new SampleAction("Download",
        new ImageIcon("action.gif"));

    // Finally, add the sample action to the menu and the toolbar.
    // These methods are no longer preferred:
    // menu.add(exampleAction);
    // toolBar.add(exampleAction);
    // Instead, you should create actual menu items and buttons:
    
    JMenuItem exampleItem = new JMenuItem(exampleAction);
    JButton exampleButton = new JButton(exampleAction);
    menu.add(exampleItem);
    toolBar.add(exampleButton);
    

    SampleAction runQuery = new SampleAction("RunQuery",
            new ImageIcon("action.gif"));
    exampleItem = new JMenuItem(runQuery);
    menu.add(exampleItem);
    

    runQuery = new SampleAction("insert",
            new ImageIcon("action.gif"));
    exampleItem = new JMenuItem(runQuery);
    menu.add(exampleItem);

     runQuery = new SampleAction("intersection",
            new ImageIcon("action.gif"));
    exampleItem = new JMenuItem(runQuery);
    menu.add(exampleItem);
    

    runQuery = new SampleAction("delete",
           new ImageIcon("action.gif"));
   exampleItem = new JMenuItem(runQuery);
   menu.add(exampleItem);

     runQuery = new SampleAction("distance",
            new ImageIcon("action.gif"));
    exampleItem = new JMenuItem(runQuery);
    menu.add(exampleItem);
    

     runQuery = new SampleAction("area",
            new ImageIcon("action.gif"));
    exampleItem = new JMenuItem(runQuery);
    menu.add(exampleItem);
    

     runQuery = new SampleAction("difference",
            new ImageIcon("action.gif"));
    exampleItem = new JMenuItem(runQuery);
    menu.add(exampleItem);
    

     runQuery = new SampleAction("length",
            new ImageIcon("action.gif"));
    exampleItem = new JMenuItem(runQuery);
    menu.add(exampleItem);
    
    

     runQuery = new SampleAction("maxMDR",
            new ImageIcon("action.gif"));
    exampleItem = new JMenuItem(runQuery);
    menu.add(exampleItem);
    
    

     runQuery = new SampleAction("MDR",
            new ImageIcon("action.gif"));
    exampleItem = new JMenuItem(runQuery);
    menu.add(exampleItem);
    
    

     runQuery = new SampleAction("minMDR",
            new ImageIcon("action.gif"));
    exampleItem = new JMenuItem(runQuery);
    menu.add(exampleItem);
    
    //exampleButton = new JButton(runQuery);
  }

  class SampleAction extends AbstractAction {
    // This is our sample action. It must have an actionPerformed() method,
    // which is called when the action should be invoked.
    public SampleAction(String text, Icon icon) {
      super(text, icon);
    }

    public void actionPerformed(ActionEvent e) {
      System.out.println("Action [" + e.getActionCommand()
          + "] performed!");
      String str = e.getActionCommand();
      if(str.equalsIgnoreCase("runQuery")){
    	  System.out.println("yes it is");
    	  OracleConnection.zing("select * FROM cola_market","zing");
      }
      if(str.equalsIgnoreCase("insert")){
    	  System.out.println("yes it is");
    	  OracleConnection.zing("select * FROM cola_market","insert");
      }
      if(str.equalsIgnoreCase("delete")){
    	  System.out.println("yes it is");
    	  OracleConnection.zing("select * FROM cola_market","delete");
      }
      if(str.equalsIgnoreCase("intersection")){
    	  System.out.println("yes it is OneQuery");
    	  OracleConnection.zing("SELECT SDO_GEOM.SDO_INTERSECTION(c_a.shape, c_c.shape, 0.005)  FROM cola_market c_a, cola_market c_c  WHERE c_a.name = 'drawG' AND c_c.name = 'drawG'","intersection");
      }
      if(str.equalsIgnoreCase("distance")){
    	  System.out.println("yes it is TwoQuery");
    	  OracleConnection.zing("SELECT SDO_GEOM.SDO_DISTANCE(c_b.shape,c_d.shape,0.005) FROM cola_markets c_b, cola_markets c_d  WHERE c_b.name = 'line' AND c_d.name = 'line'","distance");
      }
      if(str.equalsIgnoreCase("area")){
    	  System.out.println("yes it is ThreeQuery");
    	  OracleConnection.zing("SELECT name, SDO_GEOM.SDO_AREA(shape, 0.005) FROM cola_markets","area");
      }
      if(str.equalsIgnoreCase("difference")){
    	  System.out.println("yes it is FourQuery");
    	  OracleConnection.zing("SELECT SDO_GEOM.SDO_DIFFERENCE(c_a.shape, m.diminfo, c_c.shape, m.diminfo) FROM cola_markets c_a, cola_markets c_c, user_sdo_geom_metadata m WHERE m.table_name = 'COLA_MARKETS' AND m.column_name = 'SHAPE' AND c_a.name = 'line' AND c_c.name = 'name'","difference");
      }
      if(str.equalsIgnoreCase("length")){
    	  System.out.println("yes it is FiveQuery");
    	  OracleConnection.zing("SELECT c.name, SDO_GEOM.SDO_LENGTH(c.shape, m.diminfo) FROM cola_markets c, user_sdo_geom_metadata m WHERE m.table_name = 'COLA_MARKETS' AND m.column_name = 'SHAPE'","length");
      }
      if(str.equalsIgnoreCase("maxMDR")){
    	  System.out.println("yes it is SixQuery");
    	  OracleConnection.zing("SELECT SDO_GEOM.SDO_MAX_MBR_ORDINATE(c.shape, m.diminfo, 1) FROM cola_markets c, user_sdo_geom_metadata m WHERE m.table_name = 'COLA_MARKETS' AND m.column_name = 'SHAPE' AND c.name = 'circle'","maxMDR");
      }
      if(str.equalsIgnoreCase("MDR")){
    	  System.out.println("yes it is SevenQuery");
    	  OracleConnection.zing("SELECT SDO_GEOM.SDO_MBR(c.shape, m.diminfo) FROM cola_markets c, user_sdo_geom_metadata m WHERE m.table_name = 'COLA_MARKETS' AND m.column_name = 'SHAPE' AND c.name = 'line'","MDR");
      }
      if(str.equalsIgnoreCase("minMDR")){
    	  System.out.println("yes it is EightQuery");
    	  OracleConnection.zing("SELECT SDO_GEOM.SDO_MIN_MBR_ORDINATE(c.shape, m.diminfo, 1) FROM cola_markets c, user_sdo_geom_metadata m WHERE m.table_name = 'COLA_MARKETS' AND m.column_name = 'SHAPE' AND c.name = 'line'","minMDR");
      }
    }
  }

  public static void main(String s[]) {
    MenuBarExample example = new MenuBarExample();
    JFrame frame = new JFrame("Action Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setJMenuBar(example.menuBar);
    frame.getContentPane().add(example.toolBar, BorderLayout.NORTH);
    frame.setSize(200, 200);
    frame.setVisible(true);
  }
}