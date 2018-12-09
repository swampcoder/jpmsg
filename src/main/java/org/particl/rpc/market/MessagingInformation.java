package org.particl.rpc.market;

public class MessagingInformation {

   private Integer id = null;
   private DSNProtocol protocol = null;
   private String publicKey = null;
   private Integer listingItemId = null;
   private Integer listingItemTemplateId = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   
   public MessagingInformation() 
   {
      super();
   }

   public Integer getId() {
      return id;
   }

   public DSNProtocol getProtocol() {
      return protocol;
   }

   public String getPublicKey() {
      return publicKey;
   }

   public Integer getListingItemId() {
      return listingItemId;
   }

   public Integer getListingItemTemplateId() {
      return listingItemTemplateId;
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

   void setProtocol(DSNProtocol protocol) {
      this.protocol = protocol;
   }

   void setPublicKey(String publicKey) {
      this.publicKey = publicKey;
   }

   void setListingItemId(Integer listingItemId) {
      this.listingItemId = listingItemId;
   }

   void setListingItemTemplateId(Integer listingItemTemplateId) {
      this.listingItemTemplateId = listingItemTemplateId;
   }

   void setUpdatedAt(Long updatedAt) {
      this.updatedAt = updatedAt;
   }

   void setCreatedAt(Long createdAt) {
      this.createdAt = createdAt;
   }
   
   
}
