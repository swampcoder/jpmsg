package org.particl.rpc.market;

public class EscrowRatio {

   private Integer id = null;
   private Double buyer = null;
   private Double seller = null;
   private Integer escrowId = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   
   public EscrowRatio() 
   {
      super();
   }

   public Integer getId() {
      return id;
   }

   public Double getBuyer() {
      return buyer;
   }

   public Double getSeller() {
      return seller;
   }

   public Integer getEscrowId() {
      return escrowId;
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

   void setBuyer(Double buyer) {
      this.buyer = buyer;
   }

   void setSeller(Double seller) {
      this.seller = seller;
   }

   void setEscrowId(Integer escrowId) {
      this.escrowId = escrowId;
   }

   void setUpdatedAt(Long updatedAt) {
      this.updatedAt = updatedAt;
   }

   void setCreatedAt(Long createdAt) {
      this.createdAt = createdAt;
   }
   
   
}

