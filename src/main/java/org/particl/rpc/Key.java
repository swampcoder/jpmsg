package org.particl.rpc;

public class Key {

   private String address= null;
   private String publicKey= null;
   private Boolean receive = null;
   private Boolean anon = null;
   private String label = "";
   
   public Key() 
   {
      
   }
   
   @Override
   public int hashCode() 
   {
      return publicKey.hashCode();
   }
   
   @Override
   public boolean equals(Object o)
   {
      if(o instanceof Key)
      {
         Key k = (Key) o;
         return k.getPublicKey().equals(getPublicKey());
      }
      return false;
   }
   
   @Override
   public String toString() 
   {
      return "Key address=" + address + " pubkey=" + publicKey;
   }

   public String getAddress() {
      return address;
   }

   public String getPublicKey() {
      return publicKey;
   }

   public Boolean getReceive() {
      return receive;
   }

   public Boolean getAnon() {
      return anon;
   }

   public String getLabel() {
      return label;
   }

   void setAddress(String address) {
      this.address = address;
   }

   void setPublicKey(String publicKey) {
      this.publicKey = publicKey;
   }

   void setReceive(Boolean receive) {
      this.receive = receive;
   }

   void setAnon(Boolean anon) {
      this.anon = anon;
   }

   void setLabel(String label) {
      this.label = label;
   }
   
   
   
   
}
