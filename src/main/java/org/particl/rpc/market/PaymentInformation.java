package org.particl.rpc.market;

public class PaymentInformation {

   private Integer id = null;
   private PaymentType type = null;
   private Integer listingItemId = null;
   private Integer listingItemTemplateId = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   private Escrow escrow = null;
   
   public PaymentInformation() 
   {
      super();
   }

   public Integer getId() {
      return id;
   }

   public PaymentType getType() {
      return type;
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

   public Escrow getEscrow() {
      return escrow;
   }

   void setId(Integer id) {
      this.id = id;
   }

   void setType(PaymentType type) {
      this.type = type;
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

   void setEscrow(Escrow escrow) {
      this.escrow = escrow;
   }
   
   
}
