package org.particl.ui.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.xml.bind.JAXBException;

import org.particl.app.Application;
import org.particl.ui.menu.DynamicMenu;
import org.particl.util.JaxbUtil;

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
      JMenuItem saveLayout = new JMenuItem("Save Layout");
      saveLayout.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) 
         {
            AppFrame appFrame = Application.frame();
            Desktop desktop = appFrame.getSelectedDesktop();
            DesktopLayout layout = new DesktopLayout(desktop);
            try {
               String layoutXml = JaxbUtil.convertObjectToXML(layout);
               Application.getdb().stringmap(DesktopConfiguration.DesktopConfigLayoutMap).put(
                     DesktopConfiguration.DesktopConfigLayoutKeyPrefix + layout.getLayoutName(), 
                     layoutXml);
            } catch (JAXBException e1) {
               e1.printStackTrace();
            }
            
         }
      });
      saveLayout.setEnabled(Application.frame().getSelectedDesktop() != null);
      add(saveLayout);
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
      exit.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) 
         {
            
         }
      });
      add(exit);
   }
   
}
