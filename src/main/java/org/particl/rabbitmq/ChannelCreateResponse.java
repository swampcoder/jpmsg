package org.particl.rabbitmq;

public class ChannelCreateResponse {

   private final ChannelCreateRequest request;
   
   private String hostname = null;
   private Integer port = null;
   
   private String queueName = null;
   
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

   public void setResponseData(String hostname, int port, String queueName)
   {
      this.hostname = hostname;
      this.port = port;
      this.queueName = queueName;
   }
}
