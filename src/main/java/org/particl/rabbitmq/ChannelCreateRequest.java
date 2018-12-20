package org.particl.rabbitmq;

public class ChannelCreateRequest {

   //private final static String ValidRequestMarker = "1010101010101010101x";
   //private String requestMarker = ValidRequestMarker
   private long requestId = 0L;
   private String requestAddress;
   private final int maxParticipants;
   private String channelDesc = null;
   
   public ChannelCreateRequest(String requestAddress, long requestId, int maxParticipants, String channelDesc) 
   {
      super();
      this.requestId = requestId;
      this.requestAddress = requestAddress;
      this.maxParticipants = maxParticipants;
      setChannelDesc(channelDesc);
   }

   public ChannelCreateRequest(String requestAddress, long requestId) 
   {
      this(requestAddress, requestId, 0, null);
   }
   
   public String getChannelDesc() 
   {
      return channelDesc;
   }
   
   public void setChannelDesc(String channelDesc) 
   {
      if(channelDesc != null && channelDesc.length() > 128)
      {
         throw new IllegalArgumentException();
      }
      this.channelDesc = channelDesc;
   }
   
   public boolean isGroup() 
   {
      return maxParticipants > 0;
   }
   
   public int getMaxParticipants() 
   {
      return maxParticipants;
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
      return "ChannelCreateRequest id=" + requestId + "  request_address=" + requestAddress + "  max_participants=" + this.maxParticipants;
   }
}
