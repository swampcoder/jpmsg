package org.particl.rabbitmq;

import java.util.List;

public class GroupChannelQueryResponse {

   private List<GroupChannelQuery> queryGroups = null;
   
   public GroupChannelQueryResponse() 
   {
      super();
   }
   
   void setGroups(List<GroupChannelQuery> queryGroups) 
   {
      this.queryGroups = queryGroups;
   }
}
