package org.particl.rabbitmq;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.codec.binary.Base64;
import org.particl.rpc.core.IParticlCore.SMSG;
import org.particl.rpc.core.smsg.SmsgMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// TODO make more generic for monitoring of inbox,outbot,sent
public class SmsgNodeProtocolMonitor extends SmsgProtocolMonitor {

   private final ChannelRequestQueue requestQueue;
  
   public SmsgNodeProtocolMonitor(ChannelRequestQueue requestQueue, List<IParticlUserNodeListener> listeners) 
   {
      super(listeners);
      this.requestQueue =requestQueue;
   }
   
   @Override
   protected boolean processProtcolMsg(SmsgMessage smsg) {

      SmsgMessageData smsgData = new SmsgMessageData(smsg);
      if(smsgData.isNodeMsg()) 
      {
         processNodeMsg(smsgData.getMsgId(), smsgData.getData(true));
      }
      return false;
   }
   
   private boolean processNodeMsg(String msgId, String json) 
   {
      NodeMsgType msgType = NodeMsgType.lookup(msgId);
      if(msgType == null) return false;
      
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      if(msgType == NodeMsgType.CHANNEL_CREATE_REQUEST) 
      {
         ChannelCreateRequest request = (ChannelCreateRequest) gson.fromJson(json, ChannelCreateRequest.class);
         notifyMsg(msgType, request);
         requestQueue.handleRequest(request);
         return true;
      }
      else if(msgType == NodeMsgType.KEYSTORE_INIT_MESSAGE) 
      {
         ChannelTLSInitMessage tls = (ChannelTLSInitMessage) gson.fromJson(json, ChannelTLSInitMessage.class);
         notifyMsg(msgType, tls);

         return true;
         
      }
      
      return false;
   }

}
