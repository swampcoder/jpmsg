package org.particl.rpc.market;

public class MessageData {

   private Integer id = null;
   private Integer actionMessageId = null;
   private String msgId = null;
   private String version = null;
   private String received = null; // time format str
   private String sent = null; // time format str
   private String from = null;
   private String to = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   
   public MessageData() 
   {
      super();
   }

   public Integer getId() {
      return id;
   }

   public Integer getActionMessageId() {
      return actionMessageId;
   }

   public String getMsgId() {
      return msgId;
   }

   public String getVersion() {
      return version;
   }

   public String getReceived() {
      return received;
   }

   public String getSent() {
      return sent;
   }

   public String getFrom() {
      return from;
   }

   public String getTo() {
      return to;
   }

   public Long getUpdatedAt() {
      return updatedAt;
   }

   public Long getCreatedAt() {
      return createdAt;
   }

   void setId(Integer id) {
      this.id = id;
   }

   void setActionMessageId(Integer actionMessageId) {
      this.actionMessageId = actionMessageId;
   }

   void setMsgId(String msgId) {
      this.msgId = msgId;
   }

   void setVersion(String version) {
      this.version = version;
   }

   void setReceived(String received) {
      this.received = received;
   }

   void setSent(String sent) {
      this.sent = sent;
   }

   void setFrom(String from) {
      this.from = from;
   }

   void setTo(String to) {
      this.to = to;
   }

   void setUpdatedAt(Long updatedAt) {
      this.updatedAt = updatedAt;
   }

   void setCreatedAt(Long createdAt) {
      this.createdAt = createdAt;
   }
   
   
}
