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
   private List<Bid> bids = new ArrayList<Bid>();
   private List<MessagingInformation> messagingInformation = new ArrayList<MessagingInformation>();
   private List<ListingItemObject> listingItemObjects = new ArrayList<ListingItemObject>();
   private PaymentInformation paymentInfo = null;
   private Escrow escrow = null;
   private ItemInformation itemInformation = null;
   private ItemLocation itemLocation = null;
   private List<ItemImage> itemImages = new ArrayList<ItemImage>();
   private Market market = null;
   private List<ActionMessage> actionMessages = new ArrayList<ActionMessage>();
   
   public Item() 
   {
      super();
   }
   

}
