package org.particl.ui.menu;

import javax.swing.JMenu;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

abstract public class DynamicMenu extends JMenu implements MenuListener {

   abstract protected void buildMenu();
   
   public DynamicMenu(String title)
   {
      super(title);
      addMenuListener(this);
   }
   
   @Override
   public void menuCanceled(MenuEvent e) {}

   @Override
   public void menuDeselected(MenuEvent e) {}

   @Override
   public void menuSelected(MenuEvent e) {
      
      this.invalidate();
      this.removeAll();
      this.buildMenu();
      this.validate();
   }

  
}
