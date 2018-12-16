package org.particl.rpc.smsg;

import org.particl.rpc.core.IParticlCore;
import org.particl.rpc.core.IParticlCore.SmsgLocation;
import org.particl.util.PartUtil;

public class SmsgMessage {

   private String fromAddress = null;
   private String toAddress = null;
   private String msgText = null;

   private final String msgId;
   private Integer version = null;
   private Long receiveTime = null;
   private Long sentTime = null;
   private int daysRetention = 0;

   private boolean msgRead = false;
   private long expiryTime = Long.MAX_VALUE;
   private int msgSize = 0;
   private SmsgLocation msgLocation = null;
   private boolean msgPaid = false;

   public SmsgMessage(String msgId) {
      this.msgId = msgId;
      PartUtil.assertNotNull(msgId);
   }

   @Override
   public int hashCode() {
      return msgId.hashCode();
   }

   @Override
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
            + "receiveTime=" + receiveTime + " sentTime=" + sentTime + " daysRetention=" + daysRetention + " location="
            + msgLocation;
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

   public Integer getVersion() {
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

   public SmsgLocation getMsgLocation() {
      return msgLocation;
   }

   public boolean isMsgPaid() {
      return msgPaid;
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

   void setVersion(Integer version) {
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

   void setMsgLocation(SmsgLocation msgLocation) {
      this.msgLocation = msgLocation;
   }

   void setMsgPaid(boolean msgPaid) {
      this.msgPaid = msgPaid;
   }

}
