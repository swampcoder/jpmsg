package org.particl.ui.mp;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

import org.particl.app.Application;
import org.particl.rpc.mp.MarketPoller;
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
      
      Application.getService(MarketPoller.class).addMarketListingHandler(itemTable);
   }

   @Override
   protected void closeView() {
      
      Application.getService(MarketPoller.class);
   }
   
   
}
