package org.particl.rabbitmq;

import java.util.ArrayList;
import java.util.List;

// consider renaming: ChannelState
public class Channel {

   private String routingKey = null;
   private int maxParticipants = 0;
   private List<UserProfile> currentUsers = new ArrayList<UserProfile>();
   
   public Channel()
   {
      super();
   }

   public String getRoutingKey() {
      return routingKey;
   }

   public void setRoutingKey(String routingKey) {
      this.routingKey = routingKey;
   }

   public int getMaxParticipants() {
      return maxParticipants;
   }

   public void setMaxParticipants(int maxParticipants) {
      this.maxParticipants = maxParticipants;
   }

   public int getNumParticipants() {
      return currentUsers.size();
   }

   void addUser(UserProfile user) 
   {
      
   }
   
}
