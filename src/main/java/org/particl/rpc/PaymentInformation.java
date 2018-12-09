package org.particl.rpc;

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
}
