package org.particl.rpc.mp.dto;

import java.util.ArrayList;
import java.util.List;

public class Item {
   
   private Integer id;
   private String hash;
   private String seller;
   private Integer marketId;
   private Integer listingItemTemplateId;
   private Integer expiryTime;
   private Long receivedAt = null;
   private Long postedAt = null;
   private Long expiredAt = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   private List<Bid> Bids = new ArrayList<Bid>();
   private List<MessagingInformation> MessagingInformation = new ArrayList<MessagingInformation>();
   private List<ListingItemObject> ListingItemObjects = new ArrayList<ListingItemObject>();
   private PaymentInformation PaymentInformation = null;
   private ItemInformation ItemInformation = null;
   private Market Market = null;
   private List<ActionMessage> ActionMessages = new ArrayList<ActionMessage>();
   private FlaggedItem FlaggedItem = null;
   
   public Item() 
   {
      super();
   }
   
   @Override
   public String toString() 
   {
      String itemStr = "ListingItem seller=" + seller + "  id=" + id + "  hash=" + hash + "  marketId=" + marketId + "  listingItemTemplateId=" + listingItemTemplateId + " expiryTime=" + expiryTime + "\n" +
               "   ReceivedAt=" + receivedAt + "  postedAt=" + postedAt + "  expiredAt=" + expiredAt + "  updatedAt=" + updatedAt + "  createdAt=" + createdAt + "\n" +
               "   Info=" + ItemInformation + "\n" + 
               "   PaymentInfo=" + PaymentInformation + "\n" + 
               "   Market=" + Market + "\n" + 
               "   FlaggedItem=" + FlaggedItem + "\n" +
               "   Bids=" + Bids + "\n";
      for(ActionMessage actionMsg : ActionMessages) 
      {
         itemStr += "   " + actionMsg + "\n";
      }
      
      itemStr += "MessagingInfo=" + MessagingInformation + "\n";
      itemStr += "ListingItemObject=" + ListingItemObjects + "\n";
      
      return itemStr;
              
   }
   

}
