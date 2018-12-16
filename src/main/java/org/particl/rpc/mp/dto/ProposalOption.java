package org.particl.rpc.mp.dto;

public class ProposalOption {

   private Integer id = null;
   private Integer optionId = null;
   private String description = null;
   private String hash = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   
   public ProposalOption() 
   {
      super();
   }

   public Integer getId() {
      return id;
   }

   public Integer getOptionId() {
      return optionId;
   }

   public String getDescription() {
      return description;
   }

   public String getHash() {
      return hash;
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

   void setOptionId(Integer optionId) {
      this.optionId = optionId;
   }

   void setDescription(String description) {
      this.description = description;
   }

   void setHash(String hash) {
      this.hash = hash;
   }

   void setUpdatedAt(Long updatedAt) {
      this.updatedAt = updatedAt;
   }

   void setCreatedAt(Long createdAt) {
      this.createdAt = createdAt;
   }
   
   
   
}
