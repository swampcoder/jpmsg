package org.particl.ui.desktop;

import java.awt.BorderLayout;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class DesktopPanel extends JPanel {

   private final ViewOptionMenu viewOptionMenu;
   
   private JMenuBar desktopMenubar = new JMenuBar();
   
   private final Desktop desktop;
   
   public DesktopPanel(Desktop desktop) 
   {
      this.desktop = desktop;
      viewOptionMenu = new ViewOptionMenu(desktop);
      setLayout(new BorderLayout());
      add(desktopMenubar, BorderLayout.PAGE_START);
      add(desktop, BorderLayout.CENTER);
      
      desktopMenubar.add(viewOptionMenu);
   }
   
   public Desktop getDesktop() 
   {
      return desktop;
   }
   
   
}
