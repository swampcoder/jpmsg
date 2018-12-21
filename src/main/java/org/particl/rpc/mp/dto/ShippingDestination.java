package org.particl.rpc.mp.dto;

public class ShippingDestination {

   private Integer id = null;
   private String country = null;
   private ShippingAvailability shippingAvailability = null;
   private Integer itemInformationId = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   
   public ShippingDestination()
   {
      super();
   }
   
   @Override
   public String toString() 
   {
      return "ShippingDestination id=" + id + " country=" + country + " availabiltiy=" + this.shippingAvailability + " itemInfoId=" + itemInformationId;
   }

   public Integer getId() {
      return id;
   }

   public String getCountry() {
      return country;
   }

   public ShippingAvailability getShippingAvailability() {
      return shippingAvailability;
   }

   public Integer getItemInformationId() {
      return itemInformationId;
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

   void setCountry(String country) {
      this.country = country;
   }

   void setShippingAvailability(ShippingAvailability shippingAvailability) {
      this.shippingAvailability = shippingAvailability;
   }

   void setItemInformationId(Integer itemInformationId) {
      this.itemInformationId = itemInformationId;
   }

   void setUpdatedAt(Long updatedAt) {
      this.updatedAt = updatedAt;
   }

   void setCreatedAt(Long createdAt) {
      this.createdAt = createdAt;
   }
   
   
}
