package org.particl.rpc.mp.dto;

import java.util.ArrayList;
import java.util.List;

public class ActionMessage {

   private Integer id = null;
   private ActionMessageData MessageData = null;
   private ActionType action = null;
   private ActionMessageEscrow MessageEscrow = null;
   private String nonce = null;
   private Boolean accepted = null;
   private Integer listingItemId = null;
   private List<MessageObject> MessageObjects = new ArrayList<MessageObject>();
   private Long updatedAt = null;
   private Long createdAt = null;

   // todo more 
   
   public ActionMessage() 
   {
      super();
   }
   
   @Override
   public String toString() 
   {
      String actionStr = "ActionMessage id="+ id + "  actionType=" + action + "  nonce=" + nonce + "  accepted=" + accepted + " listingItemId=" + listingItemId;
      
      return actionStr;
   }
   
}
