package org.particl.rabbitmq;

import java.util.ArrayList;
import java.util.List;

// consider renameing to be more generic: ChannelCache
public class ChannelCache {

   private final List<Channel> groupChats = new ArrayList<Channel>();
   
   public ChannelCache() 
   {
      super();
   }
   
   public List<Channel> getGroupChats() 
   {
      return groupChats;
   }
   
 
}
