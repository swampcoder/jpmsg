package org.particl.rabbitmq;

import java.util.HashMap;
import java.util.Map;

public enum NodeMsgType {

   
   CHANNEL_CREATE_REQUEST("0001"), 
   CHANNEL_CREATE_RESPONSE("0002"),
   
   KEYSTORE_INIT_MESSAGE("0003");
   
   private final static Map<String, NodeMsgType> idMapping = new HashMap<String, NodeMsgType>();
   static 
   {
      for(NodeMsgType type : NodeMsgType.values())
      {
         idMapping.put(type.msgId, type);
      }
   }
   public static NodeMsgType lookup(String msgId) 
   {
      return idMapping.get(msgId);
   }
   private final String msgId;
   private NodeMsgType(String msgId)
   {
      this.msgId = msgId;
   }
   
   public String id() {
      return msgId;
   }
}
