package org.particl.rabbitmq;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.codec.binary.Base64;
import org.particl.rpc.core.ParticlJSONRPCClient;
import org.particl.rpc.core.smsg.SmsgSendFailException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import wf.bitcoin.javabitcoindrpcclient.BitcoinRPCException;

public class ParticlSmsgUserNode extends SmsgNode {

   private final List<IParticlUserNodeListener> listeners = 
         new CopyOnWriteArrayList<IParticlUserNodeListener>();
   private SmsgUserNodeMonitorThread userNodeMonitor = null;
   
   public ParticlSmsgUserNode(ParticlJSONRPCClient particl) 
   {
      super(particl);
      this.userNodeMonitor = new SmsgUserNodeMonitorThread(particl.getSMSG(), listeners);
   }
   
   public ParticlJSONRPCClient getCoreRpc() 
   {
      return particl;
   }
   
   public void addListener(IParticlUserNodeListener listener)
   {
      listeners.add(listener);
   }
   
   public void removeListener(IParticlUserNodeListener listener) 
   {
      listeners.remove(listener);
   }

   // need to input pubkey/address when node is not local tester
   public void createChannel(String to, ChannelCreateRequest request) throws BitcoinRPCException, SmsgSendFailException 
   {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String json = gson.toJson(request);
      System.out.println(json);
      String outputMsg = buildMsg(json);
      particl.getSMSG().send(request.getRequestAddress(), to, outputMsg); 
   }
   
   protected String buildMsg(String json) 
   {
      return SmsgMessageData.SMSG_BASE64_NODE_HEADER + NodeMsgType.CHANNEL_CREATE_REQUEST.id() + Base64.encodeBase64String(json.getBytes());
            
   }

}