package org.particl.rpc.mp.dto;

public class ItemLocation {

   private Integer id = null;
   private String region = null;
   private String address = null;
   private Integer itemInformationId = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   private LocationMarker locationMarker = null;
   
   public ItemLocation() 
   {
      super();
   }

   public Integer getId() {
      return id;
   }

   public String getRegion() {
      return region;
   }

   public String getAddress() {
      return address;
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
   
   public LocationMarker getLocationMarker() 
   {
      return locationMarker;
   }

   void setId(Integer id) {
      this.id = id;
   }

   void setRegion(String region) {
      this.region = region;
   }

   void setAddress(String address) {
      this.address = address;
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
   
   void setLocationMarker(LocationMarker locationMarker)
   {
      this.locationMarker = locationMarker;
   }
   
}
