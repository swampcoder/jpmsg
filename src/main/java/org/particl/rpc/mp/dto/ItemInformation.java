package org.particl.rpc.mp.dto;

import java.util.ArrayList;
import java.util.List;

public class ItemInformation {

   private Integer id = null;
   private String title = null;
   private String shortDescription = null;
   private String longDescription = null;
   private Integer itemCategoryId = null;
   private Integer listingItemId = null;
   private Integer listingItemTemplateId = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   private ItemCategory itemCategory = null;
   private ItemLocation itemLocation = null;
   private List<ItemImage> ItemImages = new ArrayList<ItemImage>();
   private List<ShippingDestination> ShippingDestinations = new ArrayList<ShippingDestination>();
   
   public ItemInformation() 
   {
      super();
   }
   
   @Override
   public String toString() 
   {
      return getClass().getSimpleName() + "  id=" + id + "  listingItemId=" + listingItemId + 
            " createdAt=" + createdAt + "  short desc: " + shortDescription + "\n" + 
            ItemImages;
   }
   
   public Integer getId() {
      return id;
   }

   public String getTitle() {
      return title;
   }

   public String getShortDescription() {
      return shortDescription;
   }

   public String getLongDescription() {
      return longDescription;
   }

   public Integer getItemCategoryId() {
      return itemCategoryId;
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

   public ItemCategory getItemCategory() {
      return itemCategory;
   }

   public ItemLocation getItemLocation() {
      return itemLocation;
   }

   public List<ItemImage> getItemImages() {
      return ItemImages;
   }

   void setId(Integer id) {
      this.id = id;
   }

   void setTitle(String title) {
      this.title = title;
   }

   void setShortDescription(String shortDescription) {
      this.shortDescription = shortDescription;
   }

   void setLongDescription(String longDescription) {
      this.longDescription = longDescription;
   }

   void setItemCategoryId(Integer itemCategoryId) {
      this.itemCategoryId = itemCategoryId;
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

   void setItemCategory(ItemCategory itemCategory) {
      this.itemCategory = itemCategory;
   }

   void setItemLocation(ItemLocation itemLocation) {
      this.itemLocation = itemLocation;
   }

   void setItemImages(List<ItemImage> itemImages) {
      this.ItemImages = itemImages;
   }
   
   
}
