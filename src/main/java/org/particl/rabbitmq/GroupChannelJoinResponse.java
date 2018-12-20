package org.particl.rabbitmq;


// reponse via particl core that the group chat join request has been accepted or not
// if the request is accepted the rabbit MQ routing key is included which is used to
// query redis for chat history and communicate on the channel.

// NOTE for redis queries and key 
public class GroupChannelJoinResponse {

   private String routingKey = null;
   
   public GroupChannelJoinResponse() 
   {
      super();
   }
   
   public String getRoutingKey() 
   {
      return routingKey;
   }
}
