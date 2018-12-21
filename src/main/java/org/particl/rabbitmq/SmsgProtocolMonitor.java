package org.particl.rabbitmq;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.particl.rpc.core.IParticlCore.SMSG;
import org.particl.rpc.core.IParticlCore.SmsgInboxMode;
import org.particl.rpc.core.smsg.SmsgMessage;
import org.particl.rpc.core.smsg.SmsgPoller;

abstract public class SmsgProtocolMonitor implements SmsgPoller.ISmsgInboxHandler {

   private final SmsgPoller poller;
   private final SMSG smsg;
   private Set<String> activeMsgs = new HashSet<String>();
   private Set<String> expiredMsgs = new HashSet<String>();
   
   private final List<IParticlUserNodeListener> listeners;
   
   abstract protected boolean processProtcolMsg(SmsgMessage smsg);
   
   public SmsgProtocolMonitor(SMSG smsg, List<IParticlUserNodeListener> listeners) 
   {
      super();
      this.smsg = smsg;
      this.listeners = listeners;
      this.poller = new SmsgPoller(smsg, false);
      this.poller.scheduleInbox(5000, null);
   }
   
   public synchronized void expireMsg(String msgid) 
   {
      expiredMsgs.add(msgid);
   }

   protected void notifyMsg(NodeMsgType msgType, Object msgObj)
   {
      if(listeners != null) 
      {
         for(IParticlUserNodeListener l : listeners) 
         {
            l.notifyUserNodeMsg(msgType, msgObj);
         }
      }
   }

   protected SMSG getSMSG() 
   {
      return smsg;
   }

   @Override
   public void notifyInbox(List<SmsgMessage> inbox) {
      
      for(SmsgMessage msg : inbox) 
      {
         if(activeMsgs.add(msg.getMsgId())) 
         {
            // process this msg for requests
            if(!processProtcolMsg(msg)) 
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
            
            // check for now since only using 1 particl instance
            
            if(getClass() != SmsgUserNodeProtocolMonitor.class)
            {
               smsg.purge(expiredId);
               System.out.println("Purging id: " + expiredId);
            }
         }
         
         activeMsgs.removeAll(expiredMsgs);
         expiredMsgs.clear();
      }
   }
}