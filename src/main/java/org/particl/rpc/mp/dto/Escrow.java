package org.particl.rpc.mp.dto;

public class Escrow {

   private Integer id = null;
   private EscrowType type = null;
   private Integer paymentInformationId = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   private EscrowRatio escrowRatio = null;
   
   public Escrow() 
   {
      super();
   }

   public Integer getId() {
      return id;
   }

   public EscrowType getType() {
      return type;
   }

   public Integer getPaymentInformationId() {
      return paymentInformationId;
   }

   public Long getUpdatedAt() {
      return updatedAt;
   }

   public Long getCreatedAt() {
      return createdAt;
   }

   public EscrowRatio getEscrowRatio() {
      return escrowRatio;
   }

   void setId(Integer id) {
      this.id = id;
   }

   void setType(EscrowType type) {
      this.type = type;
   }

   void setPaymentInformationId(Integer paymentInformationId) {
      this.paymentInformationId = paymentInformationId;
   }

   void setUpdatedAt(Long updatedAt) {
      this.updatedAt = updatedAt;
   }

   void setCreatedAt(Long createdAt) {
      this.createdAt = createdAt;
   }

   void setEscrowRatio(EscrowRatio escrowRatio) {
      this.escrowRatio = escrowRatio;
   }
   
   
}
