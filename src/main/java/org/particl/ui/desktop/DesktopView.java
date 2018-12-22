package org.particl.ui.desktop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JPanel;

abstract public class DesktopView extends JPanel  {

   private final DesktopInputData input;
   private DesktopFrame frame = null;
   
   private List<JMenu> viewMenus = new ArrayList<JMenu>();
   private String title = null;
   
   public DesktopView(DesktopInputData input) 
   {
      super();
      this.input = input;
   }
   
   public IDesktopViewFactory getFactory() 
   {
      return input.getFactory();
   }
   
   public String getTitle() 
   {
      return title;
   }
   
   public Iterator<JMenu> getMenus() 
   {
      return viewMenus.iterator();
   }
   
   public DesktopFrame getFrame() 
   {
      return frame;
   }
   
   void setFrame(DesktopFrame frame)
   {
      this.frame = frame;
   }
}
