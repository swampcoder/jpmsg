package org.particl.rpc;

import java.util.ArrayList;
import java.util.List;

public class ItemImage {

   private Integer id = null;
   private String hash = null;
   private Integer itemInformationId = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   private List<ItemImageData> itemImageData = new ArrayList<ItemImageData>();

   public ItemImage() {
      super();
   }

   public Integer getId() {
      return id;
   }

   public String getHash() {
      return hash;
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

   public List<ItemImageData> getItemImageData() {
      return itemImageData;
   }

   void setId(Integer id) {
      this.id = id;
   }

   void setHash(String hash) {
      this.hash = hash;
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

   void addItemImageData(ItemImageData imageData) {
      this.itemImageData.add(imageData);
   }
}
