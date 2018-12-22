package org.particl.ui.desktop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JTabbedPane;

public class AppFrame {

   private final JTabbedPane desktopTabs = new JTabbedPane();
   private List<Desktop> frameDesktops = new ArrayList<Desktop>();
   private final Map<String, Desktop> desktopMap = new HashMap<String, Desktop>();
   
   public AppFrame() 
   {
      super();
   }
   
   public Iterator<Desktop> getDesktops() 
   {
      return frameDesktops.iterator();
   }
   
   void addDesktop(String desktopName) throws DesktopException
   {
      if(desktopMap.containsKey(desktopName)) 
      {
         throw new DesktopException("Desktop " + desktopName + " already exists");
      }
      Desktop desktop = new Desktop(desktopName);
      desktopMap.put(desktopName, desktop);
   }
   
   Desktop removeDesktop(String desktopName) throws DesktopException 
   {
      Desktop desktop = desktopMap.remove(desktopName);
      if(desktop == null) 
      {
         throw new DesktopException("Desktop " + desktopName + " doesn't exist");
      }
      
      return desktop;
   }
}
