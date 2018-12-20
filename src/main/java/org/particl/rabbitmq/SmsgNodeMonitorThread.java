package org.particl.rabbitmq;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.codec.binary.Base64;
import org.particl.rpc.core.IParticlCore.SMSG;
import org.particl.rpc.core.IParticlCore.SmsgInboxMode;
import org.particl.rpc.core.smsg.SmsgMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// TODO make more generic for monitoring of inbox,outbot,sent
public class SmsgNodeMonitorThread extends SmsgMonitorThread {

   private final ChannelRequestQueue requestQueue;
  
   public SmsgNodeMonitorThread(SMSG smsg, ChannelRequestQueue requestQueue, List<IParticlUserNodeListener> listeners) 
   {
      super(smsg, listeners);
      this.requestQueue =requestQueue;
   }
   
   @Override
   protected boolean processMsg(SmsgMessage smsg) {

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
      
      if(msgType == NodeMsgType.CHANNEL_CREATE_REQUEST) 
      {
         Gson gson = new GsonBuilder().setPrettyPrinting().create();
         ChannelCreateRequest request = (ChannelCreateRequest) gson.fromJson(json, ChannelCreateRequest.class);
         notifyMsg(msgType, request);
         requestQueue.handleRequest(request);
         return true;
      }
      
      return false;
   }

}
