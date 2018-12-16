package org.particl.rpc.mp;

import java.net.MalformedURLException;

public class ApiTest {

   
   public static void main(String[] args) 
   {
      ParticlMarketApi api;
      try {
         api = new ParticlMarketApi("localhost", 3000, "test", "test");
         System.out.println(api.getMarketService().list());
         
      } catch (MalformedURLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
     
   }
}
