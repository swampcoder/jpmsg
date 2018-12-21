package org.particl.rpc.mp.dto;

public class PaymentInformation {

   private Integer listingItemTemplateId = null;
   private Long createdAt = null;
   private Escrow Escrow = null;
   private ItemPrice ItemPrice = null;
   private Integer id = null;
   private PaymentType type = null;
   private Integer listingItemId = null;
   
   private Long updatedAt = null;
   

   public PaymentInformation() 
   {
      super();
   }
   
   @Override
   public String toString() 
   {
      return "PaymentInfo id=" + id + " type=" + type + " listingItemId=" + listingItemId + 
            " listingItemTemplateId=" + listingItemTemplateId + " updatedAt=" + updatedAt + " createdAt=" + createdAt + "escrow=" + Escrow;
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
      return Escrow;
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
      this.Escrow = escrow;
   }
   
   
}
