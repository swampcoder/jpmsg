package org.particl.rpc.mp;

import org.particl.app.Application;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;

public class MarketBaseRpcService {

   public static final String MarketRpcMapName = "marketrpc-db";
   public static final String LastMarketRpcTimeKey = "LastMarketRpcTimeKey";
   public static final String NumMarketRpcCalls = "NumMarketRpcCalls";
   
   static 
   {
      // reset counter on each restart 
      Application.getdb().longmap(MarketRpcMapName).put(NumMarketRpcCalls, 0L);
   }
   
   private static Long requestId = 0L;

   private final JSONRPC2Session session;

   protected MarketBaseRpcService(JSONRPC2Session session) {
      super();
      this.session = session;
   }

   protected JSONRPC2Response makeRequest(JSONRPC2Request request) throws JSONRPC2SessionException {
      
      JSONRPC2Response response = session.send(request);

      Application.getdb().longmap(MarketRpcMapName).put(LastMarketRpcTimeKey, System.currentTimeMillis());
      
      return response;
   }

   public JSONRPC2Session getSession() {
      return session;
   }
   
   public static long getNumRpcCalls() 
   {
      return Application.getdb().longmap(MarketRpcMapName).get(NumMarketRpcCalls);
   }
   
   public static long getLastMarketRpcCall() 
   {
      return Application.getdb().longmap(MarketRpcMapName).get(LastMarketRpcTimeKey);
   }

   protected static Long createId() {
      synchronized (requestId) {
         long id = requestId;
         requestId++;
         return id;
      }
   }
}
