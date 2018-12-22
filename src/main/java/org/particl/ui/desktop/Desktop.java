package org.particl.ui.desktop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JDesktopPane;

import org.particl.rpc.mp.Utils;

public class Desktop extends JDesktopPane {

   private final String desktopName;
   private final List<DesktopFrame> desktopFrames = new ArrayList<DesktopFrame>();
   
   public Desktop(String desktopName) 
   {
      super();
      Utils.notNull(desktopName);
      this.desktopName = desktopName;
   }
   
   public String getDesktopName() 
   {
      return desktopName;
   }
   
   public Iterator<DesktopFrame> frames() 
   {
      return desktopFrames.iterator();
   }
   
   public int hashCode() 
   {
      return desktopName.hashCode();
   }
   
   @Override
   public boolean equals(Object cmp) 
   {
      if(cmp instanceof Desktop) 
      {
         Desktop desktop = (Desktop) cmp;
         return desktop.desktopName.equals(desktopName);
      }
      return false;
   }
}
