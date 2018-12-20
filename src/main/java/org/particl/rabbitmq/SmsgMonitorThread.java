package org.particl.rabbitmq;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.particl.rpc.core.IParticlCore.SMSG;
import org.particl.rpc.core.IParticlCore.SmsgInboxMode;
import org.particl.rpc.core.smsg.SmsgMessage;

// TODO make more generic for monitoring of inbox,outbot,sent
abstract public class SmsgMonitorThread extends TimerTask {

   private Timer monitorTimer = new Timer("Particl SMSG Monitor=" + getClass().getSimpleName());
   
   private final SMSG smsg;

   private Set<String> activeMsgs = new HashSet<String>();
   private Set<String> expiredMsgs = new HashSet<String>();
   
   private final List<IParticlUserNodeListener> listeners;
   
   abstract protected boolean processMsg(SmsgMessage smsg);
   
   public SmsgMonitorThread(SMSG smsg, List<IParticlUserNodeListener> listeners) 
   {
      super();
      this.smsg = smsg;
      this.listeners = listeners;
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
           if(!processMsg(msg)) 
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
           
           if(getClass() != SmsgUserNodeMonitorThread.class)
           {
              smsg.purge(expiredId);
              System.out.println("Purging id: " + expiredId);
           }
        }
        
        activeMsgs.removeAll(expiredMsgs);
        expiredMsgs.clear();
     }
      
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
}