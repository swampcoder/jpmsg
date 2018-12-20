package org.particl.rabbitmq;

public class ChannelCreateResponse {

   private final ChannelCreateRequest request;
   
   private String hostname = null;
   private Integer port = null;
   
   private String queueName = null;
   private String routingKey = null;
   private String pubKeySecret = null;
   
   public ChannelCreateResponse( ChannelCreateRequest request ) 
   {
      super();
      this.request = request;
   }
   
   public ChannelCreateRequest getRequest() 
   {
      return request;
   }
   
   public String getHostname() {
      return hostname;
   }

   public Integer getPort() {
      return port;
   }

   public String getQueueName() {
      return queueName;
   }
   
   public String getRoutingKey() 
   {
      return routingKey;
   }
   
   public String getPubkeySecret() 
   {
      return pubKeySecret;
   }

   void setResponseData(String hostname, int port, String queueName, String routingKey, String pubKeySecret)
   {
      this.hostname = hostname;
      this.port = port;
      this.queueName = queueName;
      this.routingKey = routingKey;
      this.pubKeySecret = pubKeySecret;
   }
}
