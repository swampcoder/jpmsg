package org.particl.rabbitmq;

import java.util.List;

import org.particl.rpc.core.IParticlCore.SMSG;
import org.particl.rpc.core.smsg.SmsgMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SmsgUserNodeProtocolMonitor extends SmsgProtocolMonitor {

   private static interface UserNodeHandler 
   {
      public void handleSmsg(SmsgMessage msg);
   }

   public SmsgUserNodeProtocolMonitor(SMSG smsg, List<IParticlUserNodeListener> listeners) {
      super(smsg, listeners);
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
      
      if(msgType == NodeMsgType.CHANNEL_CREATE_RESPONSE) 
      {
         Gson gson = new GsonBuilder().setPrettyPrinting().create();
         ChannelCreateResponse response = (ChannelCreateResponse) gson.fromJson(json, ChannelCreateResponse.class);
         notifyMsg(msgType, response);
      }
      
      return false;
   }
   

}
