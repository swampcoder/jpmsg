package org.particl.rpc.mp.dto;

public class ParentItemCategory {

   private Integer id = null;
   private String key = null;
   private String name = null;
   private String description = null;
   private Integer parentItemCategoryId = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   private ParentItemCategory parentItemCategory = null;
   
   public ParentItemCategory() 
   {
      super();
   }

   public Integer getId() {
      return id;
   }

   public String getKey() {
      return key;
   }

   public String getName() {
      return name;
   }

   public String getDescription() {
      return description;
   }

   public Integer getParentItemCategoryId() {
      return parentItemCategoryId;
   }

   public Long getUpdatedAt() {
      return updatedAt;
   }

   public Long getCreatedAt() {
      return createdAt;
   }

   public ParentItemCategory getParentItemCategory() {
      return parentItemCategory;
   }

   void setId(Integer id) {
      this.id = id;
   }

   void setKey(String key) {
      this.key = key;
   }

   void setName(String name) {
      this.name = name;
   }

   void setDescription(String description) {
      this.description = description;
   }

   void setParentItemCategoryId(Integer parentItemCategoryId) {
      this.parentItemCategoryId = parentItemCategoryId;
   }

   void setUpdatedAt(Long updatedAt) {
      this.updatedAt = updatedAt;
   }

   void setCreatedAt(Long createdAt) {
      this.createdAt = createdAt;
   }

   void setParentItemCategory(ParentItemCategory parentItemCategory) {
      this.parentItemCategory = parentItemCategory;
   }
   
   
}
