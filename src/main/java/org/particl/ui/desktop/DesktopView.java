package org.particl.ui.desktop;

import java.awt.BorderLayout;
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
   
   abstract protected void closeView();
   
   public DesktopView(DesktopInputData input) 
   {
      super();
      this.input = input;
      setLayout(new BorderLayout());
   }
   
   public IDesktopViewFactory getFactory() 
   {
      return input.getFactory();
   }
   
   public String getTitle() 
   {
      return title;
   }
   
   public Iterable<JMenu> getMenus() 
   {
      return viewMenus;
   }
   
   public DesktopFrame getFrame() 
   {
      return frame;
   }
   
   void setFrame(DesktopFrame frame)
   {
      this.frame = frame;
   }
   
   protected void setTitle(String title) 
   {
      this.title = title;
   }
}
