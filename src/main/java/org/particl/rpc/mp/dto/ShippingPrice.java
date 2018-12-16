package org.particl.rpc.mp.dto;

public class ShippingPrice {

   private Integer id = null;
   private Double domestic = null;
   private Double international = null;
   private Integer itemPriceAt = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   
   public ShippingPrice() 
   {
      
   }

   public Integer getId() {
      return id;
   }

   public Double getDomestic() {
      return domestic;
   }

   public Double getInternational() {
      return international;
   }

   public Integer getItemPriceAt() {
      return itemPriceAt;
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

   void setDomestic(Double domestic) {
      this.domestic = domestic;
   }

   void setInternational(Double international) {
      this.international = international;
   }

   void setItemPriceAt(Integer itemPriceAt) {
      this.itemPriceAt = itemPriceAt;
   }

   void setUpdatedAt(Long updatedAt) {
      this.updatedAt = updatedAt;
   }

   void setCreatedAt(Long createdAt) {
      this.createdAt = createdAt;
   }
   
   
}
