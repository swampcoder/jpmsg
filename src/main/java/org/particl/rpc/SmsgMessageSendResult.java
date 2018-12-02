package org.particl.rpc;

public class SmsgMessageSendResult {
   
   private final String msgId;
   private final String txId;
   private final double feePaid;
   public SmsgMessageSendResult(String msgId, String txId, double feePaid)
   {
      this.msgId = msgId;
      this.txId = txId;
      this.feePaid = feePaid;
   }
   
   public SmsgMessageSendResult(String msgId)
   {
      this.msgId = msgId;
      this.txId = null;
      this.feePaid = -1d;
   }

   public String getMsgId() {
      return msgId;
   }

   public String getTxId() {
      return txId;
   }

   public double getFeePaid() {
      return feePaid;
   }
   
   public boolean isPaidMsg() 
   {
      return txId != null;
   }
   
   @Override
   public String toString() 
   {
      return "SmsgMessageSendResult msgId=" + msgId + " txId=" + txId + " fee=" + feePaid;
   }
   
}
