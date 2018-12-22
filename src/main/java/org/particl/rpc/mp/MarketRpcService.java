package org.particl.rpc.mp;

public class MarketRpcService {

   private static Long requestId = 0L;

   protected MarketRpcService() 
   {
      super();
   }
   
   protected static Long createId() 
   {
      synchronized(requestId)
      {
         long id = requestId;
         requestId++;
         return id;
      }
   }
}
