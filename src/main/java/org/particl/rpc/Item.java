package org.particl.rpc;

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
   private List<ActionMessage> actionMessages = new ArrayList<ActionMessage>();
   private PaymentInformation paymentInfo = null;
   
   public Item() 
   {
      super();
   }
   
   
}
