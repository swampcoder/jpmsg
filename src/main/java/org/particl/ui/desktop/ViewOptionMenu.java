package org.particl.ui.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.particl.ui.menu.DynamicMenu;

public class ViewOptionMenu extends DynamicMenu {

   private final DesktopFrame frame;
   private final Desktop desktop;
   public ViewOptionMenu(DesktopFrame frame)
   {
      super("View");
      this.frame = frame;
      this.desktop = null;
   }
   
   public ViewOptionMenu(Desktop desktop) 
   {
      super("View");
      this.frame = null;
      this.desktop = desktop;
   }

   @Override
   protected void buildMenu() {
      
      createAddToViewMenu();
      createExitMenu();
   }
   
   private void createAddToViewMenu() 
   {

      JMenu addToView = new JMenu("Add To View");
      Iterator<String> viewGroups = Factories.getViewGroups();
      while(viewGroups.hasNext()) 
      {
         String viewGroup = viewGroups.next();
         Iterator<IDesktopViewFactory> viewFactories = 
               Factories.getViewGroupFactories(viewGroup);
         while(viewFactories.hasNext()) 
         {
            IDesktopViewFactory viewFactory = viewFactories.next();
            JMenuItem loadView = new JMenuItem(viewFactory.getViewDescription());
            loadView.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) 
               {
                  DesktopInputData inputData = createInputData();
                  viewFactory.getInput(inputData);
                  try {
                     DesktopView view = viewFactory.createView(inputData);
                     if(frame != null)
                     {
                        frame.addView(view);
                     }
                     else 
                     {
                        DesktopFrame frame = new DesktopFrame(desktop, view);
                        desktop.addFrame(frame);
                     }
                  } catch (DesktopViewException e1) {
                     e1.printStackTrace();
                  }
                  
               }
            });
            addToView.add(loadView);
         }
      }
      add(addToView);
   }
   
   private void createExitMenu() 
   {
      JMenuItem exit = new JMenuItem("Close View");
      exit.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) 
         {
            frame.closeCurrent();
         }
      });
      add(exit);
   }
   
   private DesktopInputData createInputData() 
   {
      if(frame != null) 
      {
         return new DesktopInputData(frame.getDesktop());
      }
      else
      {
         return new DesktopInputData(desktop);
      }
   }
   
}
