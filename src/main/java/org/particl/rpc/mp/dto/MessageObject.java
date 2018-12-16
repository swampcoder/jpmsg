package org.particl.rpc.mp.dto;

public class MessageObject {

   private Integer id = null;
   private Integer actionMessageId = null;
   private String dataId = null;
   private String dataValue = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   
   public MessageObject() 
   {
      super();
   }

   public Integer getId() {
      return id;
   }

   public Integer getActionMessageId() {
      return actionMessageId;
   }

   public String getDataId() {
      return dataId;
   }

   public String getDataValue() {
      return dataValue;
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

   void setDataId(String dataId) {
      this.dataId = dataId;
   }

   void setDataValue(String dataValue) {
      this.dataValue = dataValue;
   }

   void setUpdatedAt(Long updatedAt) {
      this.updatedAt = updatedAt;
   }

   void setCreatedAt(Long createdAt) {
      this.createdAt = createdAt;
   }
   
   
}
