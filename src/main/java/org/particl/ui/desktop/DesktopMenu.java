package org.particl.ui.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.particl.ui.menu.DynamicMenu;

public class DesktopMenu extends DynamicMenu {

   
   public DesktopMenu(String title) {
      super(title);
   }

   @Override
   protected void buildMenu() {
      
      
      addCreateDesktop();
      
   }
   
   private void addCreateDesktop() 
   {
      JMenuItem createDesktop = new JMenuItem("Create Desktop");
      createDesktop.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) 
         {
            
         }
      });
      add(createDesktop);
   }

}
