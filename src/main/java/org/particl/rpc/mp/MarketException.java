package org.particl.rpc.mp;

public class MarketException extends Exception {

   public MarketException(MarketResult result, String msg, Exception cause)
   {
      super(msg, cause);
   }
}
