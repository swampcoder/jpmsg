package org.particl.rabbitmq;

// notify that the group chat routing key has been changed 
// this message must be sent over the particl core interface


public class GroupChannelJumpNotification {
   
   private String oldRoutingKey = null;
   private String newRoutingKey = null;
   
   public GroupChannelJumpNotification(String oldRoutingKey, String newRoutingKey) 
   {
      super();
      this.oldRoutingKey = oldRoutingKey;
      this.newRoutingKey = newRoutingKey;
   }

   public String getOldRoutingKey() 
   {
      return oldRoutingKey;
   }
   
   public String getNewRoutingKey() 
   {
      return newRoutingKey;
   }
}
