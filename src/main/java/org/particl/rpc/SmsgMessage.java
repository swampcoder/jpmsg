package org.particl.rpc;

import org.particl.util.PartUtil;

public class SmsgMessage {

   private String fromAddress = null;
   private String toAddress = null;
   private String msgText = null;

   private final String msgId;
   private String version = null;
   private Long receiveTime = null;
   private Long sentTime = null;
   private int daysRetention = 0;
   
   // fields part of smsg view command
   private boolean msgRead = false;
   private long expiryTime = Long.MAX_VALUE;
   private int msgSize = 0;
   

   public SmsgMessage(String msgId) {
      this.msgId = msgId;
      PartUtil.assertNotNull(msgId);
   }

   public int hashCode() {
      return msgId.hashCode();
   }

   public boolean equals(Object obj) {
      if (obj instanceof SmsgMessage) {
         SmsgMessage msgCmp = (SmsgMessage) obj;
         return msgCmp.msgId.equals(msgId);
      }
      return false;
   }

   @Override
   public String toString() {
      return "SmsgMessage from=" + fromAddress + " to=" + toAddress + " msgId=" + msgId + " version=" + version
            + "receiveTime=" + receiveTime + " sentTime=" + sentTime + " daysRetention=" + daysRetention;
   }

   public String getFromAddress() {
      return fromAddress;
   }

   public String getToAddress() {
      return toAddress;
   }

   public String getMsgText() {
      return msgText;
   }

   public String getMsgId() {
      return msgId;
   }

   public String getVersion() {
      return version;
   }

   public Long getReceiveTime() {
      return receiveTime;
   }

   public Long getSentTime() {
      return sentTime;
   }

   public int getDaysRetention() {
      return daysRetention;
   }

   public boolean isMsgRead() {
      return msgRead;
   }

   public long getExpiryTime() {
      return expiryTime;
   }

   public int getMsgSize() {
      return msgSize;
   }

   void setFromAddress(String fromAddress) {
      this.fromAddress = fromAddress;
   }

   void setToAddress(String toAddress) {
      this.toAddress = toAddress;
   }

   void setMsgText(String msgText) {
      this.msgText = msgText;
   }

   void setVersion(String version) {
      this.version = version;
   }

   void setReceiveTime(Long receiveTime) {
      this.receiveTime = receiveTime;
   }

   void setSentTime(Long sentTime) {
      this.sentTime = sentTime;
   }

   void setDaysRetention(int daysRetention) {
      this.daysRetention = daysRetention;
   }

   void setMsgRead(boolean msgRead) {
      this.msgRead = msgRead;
   }

   void setExpiryTime(long expiryTime) {
      this.expiryTime = expiryTime;
   }

   void setMsgSize(int msgSize) {
      this.msgSize = msgSize;
   }
   
   

}
