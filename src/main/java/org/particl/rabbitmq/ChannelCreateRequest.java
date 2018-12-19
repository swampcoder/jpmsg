package org.particl.rabbitmq;

public class ChannelCreateRequest {

   //private final static String ValidRequestMarker = "1010101010101010101x";
   //private String requestMarker = ValidRequestMarker
   private long requestId = 0L;
   private String requestAddress;
   public ChannelCreateRequest(String requestAddress, long requestId) 
   {
      super();
      this.requestId = requestId;
      this.requestAddress = requestAddress;
   }

   public long getRequestId() 
   {
      return requestId;
   }
   
   public String getRequestAddress() 
   {
      return requestAddress;
   }
   
   @Override
   public String toString() 
   {
      return "ChannelCreateRequest id=" + requestId + "  request_address=" + requestAddress;
   }
}
