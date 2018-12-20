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
public class ParticlRequestMonitorThread extends TimerTask {

   private final ChannelRequestQueue requestQueue;
   
   private Timer monitorTimer = new Timer("Particl SMSG Inbox Monitor");
   
   private final SMSG smsg;

   private Set<String> activeMsgs = new HashSet<String>();
   private Set<String> expiredMsgs = new HashSet<String>();
   
   public ParticlRequestMonitorThread(SMSG smsg, ChannelRequestQueue requestQueue) 
   {
      super();
      this.requestQueue =requestQueue;
      this.smsg = smsg;
      
      monitorTimer.schedule(this, 1000L, 5000L);
   }
   
   public synchronized void expireMsg(String msgid) 
   {
      expiredMsgs.add(msgid);
   }

   @Override
   public void run() {
 
     List<SmsgMessage> inboxMessages = smsg.inbox(SmsgInboxMode.All, null);
     
     for(SmsgMessage msg : inboxMessages) 
     {
        if(activeMsgs.add(msg.getMsgId())) 
        {
           // process this msg for requests
           if(!checkForRequests(msg)) 
           {
              expiredMsgs.add(msg.getMsgId());
           }
        }
     }

     synchronized (this) 
     {

        for(String expiredId : expiredMsgs) 
        {
           // delete
           smsg.purge(expiredId);
           System.out.println("Purging id: " + expiredId);
        }
        
        activeMsgs.removeAll(expiredMsgs);
        expiredMsgs.clear();
     }
      
   }
   
   private boolean checkForRequests(SmsgMessage msg) 
   {
      try
      {
         String decodedMsg = new String(Base64.decodeBase64(msg.getMsgText()));
         System.out.println("Received request: " + msg.getMsgText());
         Gson gson = new GsonBuilder().setPrettyPrinting().create();
         ChannelCreateRequest request = gson.fromJson(decodedMsg, ChannelCreateRequest.class);
         
         // learn more about validating TODO 
         if(request != null && request.getRequestAddress() != null)
         {
            requestQueue.handleRequest(request);
            System.out.println("Processed request: " + request);   
            return true;
         }
         
      } catch(Exception e ) 
      {
         // ignore - parse error (add validation)
      }
      return false;
   }
}
