package org.particl.rpc.mp;

public class MarketResponse {

   private final MarketResult result;
   private String rawResponse = null;
   
   public MarketResponse(MarketResult result)
   {
      this.result = result;
   }
   
   public MarketResponse(MarketResult result, String rawResponse)
   {
      this.result = result;
      this.rawResponse = rawResponse;
   }
   
   
   public MarketResponse(MarketResult result, Exception error)
   {
      this.result = result;

   }
   
   public boolean isSuccess() 
   {
      return result == MarketResult.Success;
   }
   
   public MarketResult getResult() 
   {
      return result;
   }
}
