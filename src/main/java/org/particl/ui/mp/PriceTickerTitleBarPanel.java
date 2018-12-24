package org.particl.ui.mp;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.particl.app.Application;

import com.jtattoo.plaf.BaseRootPaneUI;

public class PriceTickerTitleBarPanel extends JPanel {

   private final JLabel priceTicker = new JLabel("2.54 USD");
   
   public PriceTickerTitleBarPanel() 
   {
   // We need a normal panel to place it in the titlebar.
      setLayout(new TitlebarLayoutManager());
      add(priceTicker);
      // The panel should be transparent for better visual appearance.
      setOpaque(false);

    /*  // Adding the controls to the titlebarPanel. The TitlebarLayoutManager will be responsible for the positions.
      titlebarPanel.add(checkBox);
      titlebarPanel.add(button);
      titlebarPanel.add(menuButton);*/
      
      // Customize the titlebar. This could only be done if one of the JTattoo look and feels
      // is active. So check this first.
      if (Application.frame().getJFrame().getRootPane().getUI() instanceof BaseRootPaneUI) {
          BaseRootPaneUI rootPaneUI = (BaseRootPaneUI)Application.frame().getJFrame().getRootPane().getUI();
          // Here is the magic. Just add the panel to the titlebar
          rootPaneUI.getTitlePane().setCustomizedTitlePanel(this);
      }
   }
}
