package org.particl.rpc.smsg;

public class SmsgSendFailException extends Exception {

   
   public SmsgSendFailException(String errMsg)
   {
      super(errMsg);
   }
}
