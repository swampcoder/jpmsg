package org.particl.rpc;

public class LocationMarker {

   private Integer id = null;
   private String markerTitle = null;
   private String markerText = null;
   private Double lat = null;
   private Double lng = null;
   private Integer itemLocationId = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   
   public LocationMarker() 
   {
      super();
   }

   public Integer getId() {
      return id;
   }

   public String getMarkerTitle() {
      return markerTitle;
   }

   public String getMarkerText() {
      return markerText;
   }

   public Double getLat() {
      return lat;
   }

   public Double getLng() {
      return lng;
   }

   public Integer getItemLocationId() {
      return itemLocationId;
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

   void setMarkerTitle(String markerTitle) {
      this.markerTitle = markerTitle;
   }

   void setMarkerText(String markerText) {
      this.markerText = markerText;
   }

   void setLat(Double lat) {
      this.lat = lat;
   }

   void setLng(Double lng) {
      this.lng = lng;
   }

   void setItemLocationId(Integer itemLocationId) {
      this.itemLocationId = itemLocationId;
   }

   void setUpdatedAt(Long updatedAt) {
      this.updatedAt = updatedAt;
   }

   void setCreatedAt(Long createdAt) {
      this.createdAt = createdAt;
   }
   
   
}
