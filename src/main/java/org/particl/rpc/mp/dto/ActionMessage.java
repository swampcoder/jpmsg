package org.particl.rpc.mp.dto;

public class ActionMessage {

   private Integer id = null;
   private ActionType action = null;
   private String nonce = null;
   private Boolean accepted = null;
   private Integer listingItemId = null;
   private Long updatedAt = null;
   private Long createdAt = null;

   // todo more 
   
   public ActionMessage() 
   {
      super();
   }
}
