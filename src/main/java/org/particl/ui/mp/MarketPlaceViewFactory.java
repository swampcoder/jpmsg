package org.particl.ui.mp;

import org.particl.rpc.mp.MarketPoller;

abstract public class MarketPlaceViewFactory {

   static 
   {
      MarketPoller poller = new MarketPoller(true);
      poller.scheduleMarketListings(5000L);
   }
   
}
