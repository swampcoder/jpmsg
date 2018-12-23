package org.particl.ui.desktop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class AppFrame implements IFrame {

   private JFrame frame = new JFrame();
   private final JPanel appPane = new JPanel();
   private final JTabbedPane desktopTabs = new JTabbedPane();
   private List<DesktopPanel> frameDesktops = new ArrayList<DesktopPanel>();
   private final Map<String, DesktopPanel> desktopMap = new HashMap<String, DesktopPanel>();
   
   public AppFrame() 
   {
      super();
      appPane.setLayout(new BorderLayout());
      appPane.add(desktopTabs, BorderLayout.CENTER);
      frame.getContentPane().add(appPane);
      appPane.setPreferredSize(new Dimension(800,700));
      frame.pack();
      frame.setVisible(true);
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
