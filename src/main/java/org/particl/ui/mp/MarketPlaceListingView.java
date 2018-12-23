package org.particl.ui.mp;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

import org.particl.ui.desktop.DesktopInputData;
import org.particl.ui.desktop.DesktopView;

public class MarketPlaceListingView extends DesktopView {

   private final MarketItemTable itemTable;
   
   public MarketPlaceListingView(DesktopInputData viewInput) 
   {
      super(viewInput);
      
      setTitle("Market Listings");
   
      itemTable = new MarketItemTable();
      add(new JScrollPane(itemTable), BorderLayout.CENTER);
   }

   @Override
   protected void closeView() {
      
   }
   
   
}
