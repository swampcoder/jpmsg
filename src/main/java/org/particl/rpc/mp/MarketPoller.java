package org.particl.rpc.mp;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.particl.app.Application;
import org.particl.app.IAppService;
import org.particl.rpc.mp.dto.Item;
import org.particl.rpc.mp.dto.Market;

public class MarketPoller implements IAppService {

   static 
   {
      MarketPoller poller = new MarketPoller(false);
      Application.initService(poller, MarketPoller.class);
      
      poller.scheduleMarketListings(5000L);
   }
   
   public static interface IMarketItemHandler
   {
      public void notifyMarketListings(List<Item> listings);
   }
   
   public static interface IMarketHandler
   {
      public void notifyMarkets(List<Market> markets);
   }

   private Timer marketlistings = null;
   private Timer markets = null;

   private boolean fixedTimeMode = false;
   
   private final List<IMarketItemHandler> marketListingHandlers = new ArrayList<IMarketItemHandler>();
   private final List<IMarketHandler> marketHandlers = new ArrayList<IMarketHandler>();
   
   public MarketPoller (boolean fixedTimeMode) {
      this.fixedTimeMode = fixedTimeMode;
   }
   
   public void executeMarketListings( ) 
   {
      List<Item> items = marketapi().getItemService().search(null);
      synchronized(marketListingHandlers)
      {
         for(IMarketItemHandler h : marketListingHandlers)
         {
            h.notifyMarketListings(items);
         }
      }
   }
   
   public void executeMarkets( ) 
   {
      List<Market> markets = marketapi().getMarketService().list();
      synchronized(marketHandlers)
      {
         for(IMarketHandler h : marketHandlers)
         {
            h.notifyMarkets(markets);
         }
      }
   }
 
   public synchronized void scheduleMarketListings(long frequencyMs )
   {
      if(marketlistings != null) marketlistings.cancel();
      marketlistings = new Timer("market poll marketlistings");  
      schedule(marketlistings, new TimerTask() {
         @Override
         public void run( ) {
            if(marketListingHandlers.size() > 0) 
            {
               pollMarketListings();
            }
         }
      }, frequencyMs);
   }
   
   public synchronized void scheduleMarkets(long frequencyMs )
   {
      if(markets != null) markets.cancel();
      markets = new Timer("market poll markets");  
      schedule(markets, new TimerTask() {
         @Override
         public void run( ) {
            if(marketHandlers.size() > 0) 
            {
               pollMarkets();
            }
         }
      }, frequencyMs);
   }
 
   public void addMarketListingHandler(IMarketItemHandler handler) 
   {
      synchronized(marketListingHandlers)
      {
         marketListingHandlers.add(handler);
      }
   }
   
   public void addMarketHandler(IMarketHandler handler) 
   {
      synchronized(marketHandlers)
      {
         marketHandlers.add(handler);
      }
   }
 
   public void cancel() 
   {
      if(marketlistings != null) marketlistings.cancel();
      if(markets != null) markets.cancel();
   }
   
   private void pollMarketListings() 
   {
      List<Item> listings = marketapi().getItemService().search(null); //.smsgKeys();
      synchronized(marketListingHandlers)
      {
         for(IMarketItemHandler h : marketListingHandlers)
         {
            h.notifyMarketListings(listings);
         }
      }
   }
   
   private void pollMarkets() 
   {
      List<Market> markets = marketapi().getMarketService().list();
      synchronized(marketListingHandlers)
      {
         for(IMarketHandler h : marketHandlers)
         {
            h.notifyMarkets(markets);
         }
      }
   }

   private void schedule(Timer timer, TimerTask task, long frequencyMs) 
   {
      if(fixedTimeMode) 
      {
         timer.scheduleAtFixedRate(task, 0, frequencyMs);
      }
      else
      {
         timer.schedule(task, 0, frequencyMs);
      }
   }
   
   private ParticlMarketApi marketapi() 
   {
      return Application.getService(ParticlConnection.class).market();
   }
}
