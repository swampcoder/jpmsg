package org.particl.rpc.mp.dto;

import java.util.ArrayList;
import java.util.List;

public class ItemImage {

   private Integer id = null;
   private String hash = null;
   private Integer itemInformationId = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   private List<ItemImageData> ItemImageDatas = new ArrayList<ItemImageData>();

   public ItemImage() {
      super();
   }
   
   @Override
   public String toString() 
   {
      return "ItemImage hash=" + hash + " itemInformationId=" + itemInformationId + "  \n" + 
            ItemImageDatas;
   }
   
   @Override
   public int hashCode() 
   {
      return hash.hashCode();
   }
   
   @Override
   public boolean equals(Object cmp) 
   {
      if(cmp instanceof ItemImage) 
      {
         ItemImage img = (ItemImage) cmp;
         return img.hash.equals(hash);
      }
      return false;
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
      return ItemImageDatas;
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
      this.ItemImageDatas.add(imageData);
   }
}
