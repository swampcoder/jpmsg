package org.particl.rpc.mp.market;

public class MarketAddRequest {

   private String name = null;
   private String privateKey = null;
   private String address = null;
   
   public MarketAddRequest(String name, String privateKey, String address)
   {
      this.name = name;
      this.privateKey = privateKey;
      this.address = address;
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
   
   
}
