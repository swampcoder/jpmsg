package org.particl.rpc.mp.dto;

import java.util.ArrayList;
import java.util.List;

public class Bid {


   private Integer id = null;
   private ActionType action = null;
   
   private Integer listingItemId = null;
   private Integer addressId = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   private List<BidData> bidData = new ArrayList<BidData>();

   public Bid() 
   {
      
   }
}
