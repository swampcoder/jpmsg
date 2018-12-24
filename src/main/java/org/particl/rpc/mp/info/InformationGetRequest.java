package org.particl.rpc.mp.info;

import org.particl.rpc.mp.Utils;

public class InformationGetRequest {

   private Integer listingItemId = null;
   private String hash = null;
   
   public InformationGetRequest(Integer listingItemId) 
   {
      Utils.notNull(listingItemId);
      this.listingItemId = listingItemId;
   }
   
   public InformationGetRequest(String hash) 
   {
      Utils.notNull(hash);
      this.hash = hash;
   }

   public Integer getListingItemId() {
      return listingItemId;
   }

   public String getHash() {
      return hash;
   }
   
   
   
}
