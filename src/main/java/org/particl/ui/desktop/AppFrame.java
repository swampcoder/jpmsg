package org.particl.ui.desktop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import org.sexydock.tabs.jhrome.JhromeTabbedPaneUI;

public class AppFrame implements IFrame{

   private JFrame frame = new JFrame();
   private final JPanel appPane = new JPanel();
   private final JTabbedPane desktopTabs = new JhromeTabbedPane();
   private List<DesktopPanel> frameDesktops = new ArrayList<DesktopPanel>();
   private final Map<String, DesktopPanel> desktopMap = new HashMap<String, DesktopPanel>();
   private final JMenuBar menubar = new JMenuBar();
   
   public AppFrame() 
   {
      super();
      
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() 
         {
            frame.setJMenuBar(menubar);
            appPane.setLayout(new BorderLayout());
            appPane.add(desktopTabs, BorderLayout.CENTER);
            frame.getContentPane().add(appPane);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            appPane.setPreferredSize(new Dimension(800,700));
            frame.pack();
            frame.setVisible(true);
         }
      });
   }
   
   public void loadLayout(DesktopLayout layout) 
   {
      Desktop desktop = new Desktop(layout);
      DesktopPanel desktopPanel = new DesktopPanel(desktop);
      desktopMap.put(layout.getLayoutName(), desktopPanel);
      desktopTabs.addTab(layout.getLayoutName(), desktopPanel);
   }
   
   public Desktop getSelectedDesktop() 
   {
      DesktopPanel desktopPanel = (DesktopPanel) desktopTabs.getComponentAt(desktopTabs.getSelectedIndex());
      return desktopPanel.getDesktop();
   }
   
   public JMenuBar getJMenubar() 
   {
      return menubar;
   }
   
   public JFrame getJFrame() 
   {
      return frame;
   }
   
   public Iterator<DesktopPanel> getDesktops() 
   {
      return frameDesktops.iterator();
   }
   
   public DesktopPanel addDesktop(String desktopName) throws DesktopException
   {
      if(desktopMap.containsKey(desktopName)) 
      {
         throw new DesktopException("Desktop " + desktopName + " already exists");
      }
      Desktop desktop = new Desktop(desktopName);
      DesktopPanel desktopPanel = new DesktopPanel(desktop);
      desktopMap.put(desktopName, desktopPanel);
      desktopTabs.addTab(desktopName, desktopPanel);
      return desktopPanel;
   }
   
   public DesktopPanel removeDesktop(String desktopName) throws DesktopException 
   {
      DesktopPanel desktopPanel = desktopMap.remove(desktopName);
      if(desktopPanel == null) 
      {
         throw new DesktopException("Desktop " + desktopName + " doesn't exist");
      }
      return desktopPanel;
   }
   
   public void setStatusBar(JPanel statusPanel)
   {
      appPane.add(statusPanel, BorderLayout.SOUTH);
   }

   @Override
   public JFrame frame() {

      return frame;
   }
}
