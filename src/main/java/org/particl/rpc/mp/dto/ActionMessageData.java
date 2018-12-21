package org.particl.rpc.mp.dto;

public class ActionMessageData {

   private Long createdAt = null;
   private Integer actionMessageId = null;
   private String msgId = null;
   private String received = null;
   private String from = null;
   private Integer id = null;
   private String to = null;
   private String version = null;
   private String sent = null;
   private Long updatedAt = null;
   
   public ActionMessageData() 
   {
      super();
   }
   
   @Override
   public String toString() 
   {
      return "ActionMessageData createdAt=" + createdAt + "  actionMsgId=" + actionMessageId + "  msgId=" + msgId + "  received=" + received + "  from=" + from + "  id=" + id + 
            "to=" + to + "  version=" + version + "  sent=" + sent + " updatedAt=" + updatedAt;
   }
}
