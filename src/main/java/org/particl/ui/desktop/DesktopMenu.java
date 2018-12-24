package org.particl.ui.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.particl.app.Application;
import org.particl.ui.menu.DynamicMenu;

public class DesktopMenu extends DynamicMenu {

   private final DesktopConfiguration config = Application.getService(DesktopConfiguration.class);
   
   public DesktopMenu( ) {
      super("Desktop");
   }

   @Override
   protected void buildMenu() {

      addCreateDesktop();
      
      addSaveLayout();
      
      addLoadLayout();
      
      addExit();
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

   private void addSaveLayout() 
   {
      
   }
   
   private void addLoadLayout() 
   {
      JMenu loadLayoutMenu = new JMenu("Load Layout");
      
      Iterator<DesktopLayout> layouts = config.getLayouts();
      while(layouts.hasNext()) 
      {
         DesktopLayout layout = layouts.next();
         
         JMenuItem loadLayout = new JMenuItem(layout.getLayoutName());
         
         loadLayout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
               Application.frame().loadLayout(layout);
            }
         });
         loadLayoutMenu.add(loadLayout);
      }
      add(loadLayoutMenu);
   }
   
   private void addExit() 
   {
      JMenuItem exit = new JMenuItem("Exit Application");
      
      add(exit);
   }
   
}
