package org.particl.rpc.mp.dto;

public class Market {

   private Integer id;
   private String name;
   private String privateKey;
   private String address;
   private Long updatedAt = null;
   private Long createdAt = null;
         
   public Market() 
   {
      super();
   }
   
   @Override
   public String toString() 
   {
      return "Market id=" + id + " name=" + name + " privKey=" + privateKey + 
            " addr=" + address + " updated=" + updatedAt + " createdAt=" + createdAt;
   }

   public Integer getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public String getPrivateKey() {
      return privateKey;
   }

   public String getAddress() {
      return address;
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

   void setName(String name) {
      this.name = name;
   }

   void setPrivateKey(String privateKey) {
      this.privateKey = privateKey;
   }

   void setAddress(String address) {
      this.address = address;
   }

   void setUpdatedAt(Long updatedAt) {
      this.updatedAt = updatedAt;
   }

   void setCreatedAt(Long createdAt) {
      this.createdAt = createdAt;
   }
   
   
}
