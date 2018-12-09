package org.particl.rpc;

public class CyptoCurrencyAddress {

   private Integer id = null;
   private CryptoAddressType type = null;
   private String address = null;
   private Integer profileId = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   
   public CyptoCurrencyAddress() 
   {
      super();
   }

   public Integer getId() {
      return id;
   }

   public CryptoAddressType getType() {
      return type;
   }

   public String getAddress() {
      return address;
   }

   public Integer getProfileId() {
      return profileId;
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

   void setType(CryptoAddressType type) {
      this.type = type;
   }

   void setAddress(String address) {
      this.address = address;
   }

   void setProfileId(Integer profileId) {
      this.profileId = profileId;
   }

   void setUpdatedAt(Long updatedAt) {
      this.updatedAt = updatedAt;
   }

   void setCreatedAt(Long createdAt) {
      this.createdAt = createdAt;
   }
   
   
   
   
}
