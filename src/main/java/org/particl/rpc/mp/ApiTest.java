package org.particl.rpc.mp;

import java.net.MalformedURLException;
import java.util.UUID;

import org.particl.rpc.mp.market.MarketAddRequest;

public class ApiTest {


   public static void main(String[] args) 
   {
      ParticlMarketApi api;
      try {
         api = new ParticlMarketApi("localhost", 3000, "test", "test", null);
         System.out.println(api.getMarketService().list());
         
         api.getMarketService().add(UUID.randomUUID().toString(), "tessst3", "ss");
         
         api.getItemService().search(null);
         
         api.getItemService().get(null);
         
      } catch (MalformedURLException | MarketException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
     
   }
}
