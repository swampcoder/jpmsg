package org.particl.rpc.core.smsg;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.particl.rpc.core.IParticlCore.SMSG;

public class SmsgPoller {

   public static interface ISmsgInboxHandler
   {
      public void notifyInbox(List<SmsgMessage> inbox);
   }
   
   public static interface ISmsgOutboxHandler 
   {
      public void notifyOutbox(List<SmsgMessage> outbox);
   }
   
   public static interface ISmsgKeyHandler 
   {
      public void notifyLocalKeys(List<SmsgKey> keys);
   }
   
   public static interface ISmsgViewHandler
   {
      public void notifyViev(List<SmsgMessage> matches);
   }
   
   public static interface ISmsgBucketHandler 
   {
      public void notifyBuckets(List<SmsgBucket> buckets);
   }
   
   private final SMSG smsg;
   
   private Timer smsglocalkeys = null;
   private Timer smsgoutbox= null;
   private Timer smsginbox = null;
   private Timer smsgview = null;
   private Timer smsgstats = null;
   
   private String outboxFilter = null;
   private String inboxFilter = null;
   private String viewFilter = null;
   
   private boolean fixedTimeMode = false;
   
   private final List<ISmsgInboxHandler> inboxHandlers = new ArrayList<ISmsgInboxHandler>();
   private final List<ISmsgOutboxHandler> outboxHandlers = new ArrayList<ISmsgOutboxHandler>();
   private final List<ISmsgKeyHandler> keyHandlers = new ArrayList<ISmsgKeyHandler>();
   private final List<ISmsgViewHandler> viewHandlers = new ArrayList<ISmsgViewHandler>();
   private final List<ISmsgBucketHandler> bucketHandlers = new ArrayList<ISmsgBucketHandler>();
   
   public SmsgPoller (SMSG smsg, boolean fixedTimeMode) {
      this.smsg = smsg;
      this.fixedTimeMode = fixedTimeMode;
   }
   
   public synchronized void scheduleLocalKeys(long frequencyMs )
   {
      if(smsglocalkeys != null) smsglocalkeys.cancel();
      smsglocalkeys = new Timer("smsg poll smsglocalkeys");  
      schedule(smsglocalkeys, new TimerTask() {
         @Override
         public void run( ) {
            if(keyHandlers.size() > 0) 
            {
               pollLocalKeys();
            }
         }
      }, frequencyMs);
   }
   
   public synchronized void scheduleOutbox(long frequencyMs, String filter) 
   {
      if(smsgoutbox != null) smsgoutbox.cancel();
      outboxFilter = filter;
      smsgoutbox  = new Timer("smsg poll outbox");
      schedule(smsgoutbox, new TimerTask() {
         @Override
         public void run( ) {
            if(outboxHandlers.size() > 0) 
            {
               pollOutbox();
            }
         }
      }, frequencyMs);
   }
   
   public synchronized void scheduleInbox(long frequencyMs, String filter)
   {
      if(smsginbox != null) smsginbox.cancel();
      inboxFilter = filter;
      smsginbox = new Timer("smsg poll inbox");
      schedule(smsginbox, new TimerTask() {
         @Override
         public void run( ) {
            if(inboxHandlers.size() > 0) 
            {
               pollInbox();
            }
         }
      }, frequencyMs);
   }
   
   public synchronized void scheduleView(long frequencyMs, String filter)
   {
      if(smsgview != null) smsgview.cancel();
      viewFilter = filter;
      smsgview = new Timer("smsg poll view");
      schedule(smsgview, new TimerTask() {
         @Override
         public void run( ) {
            if(viewHandlers.size() > 0) 
            {
               pollView();
            }
         }
      }, frequencyMs);
   }
   
   public synchronized void scheduleBuckets(long frequencyMs)
   {
      if(smsgstats != null) smsgstats.cancel();
      smsgstats = new Timer("smsg poll buckets");
      schedule(smsgstats, new TimerTask() {
         @Override
         public void run( ) {
            if(bucketHandlers.size() > 0) 
            {
               pollBuckets();
            }
         }
      }, frequencyMs);
   }
   
   public void addInboxHandler(ISmsgInboxHandler handler) 
   {
      synchronized(inboxHandlers)
      {
         inboxHandlers.add(handler);
      }
   }
   
   public void addOutboxHandler(ISmsgOutboxHandler handler) 
   {
      synchronized(outboxHandlers)
      {
         outboxHandlers.add(handler);
      }
   }
   
   public void addLocalKeyHandler(ISmsgKeyHandler handler)
   {
      synchronized(keyHandlers) 
      {
         keyHandlers.add(handler);
      }
   }
   
   public void addViewHandler(ISmsgViewHandler handler)
   {
      synchronized(viewHandlers)
      {
         viewHandlers.add(handler);
      }
   }
   
   public void addBucketHandler(ISmsgBucketHandler handler) 
   {
      synchronized(bucketHandlers)
      {
         bucketHandlers.add(handler);
      }
   }

   public void cancel() 
   {
      if(smsginbox != null) smsginbox.cancel();
      if(smsgoutbox != null) smsgoutbox.cancel();
      if(smsglocalkeys != null) smsglocalkeys.cancel();
      if(smsgview != null) smsgview.cancel();
      if(smsgstats != null) smsgstats.cancel();
   }
   
   private void pollLocalKeys() 
   {
      List<SmsgKey> keys = smsg.smsgKeys();
      synchronized(keyHandlers)
      {
         for(ISmsgKeyHandler h : keyHandlers)
         {
            h.notifyLocalKeys(keys);
         }
      }
   }
   
   private void pollOutbox() 
   {
      List<SmsgMessage> msgs = smsg.outbox(SmsgOutboxMode.All, outboxFilter);
      
      synchronized(outboxHandlers)
      {
         for(ISmsgOutboxHandler h : outboxHandlers)
         {
            h.notifyOutbox(msgs);
         }
      }
   }
   
   private void pollInbox() 
   {
      List<SmsgMessage> msgs = smsg.inbox(SmsgInboxMode.All, inboxFilter);

      synchronized(inboxHandlers)
      {
         for(ISmsgInboxHandler h : inboxHandlers)
         {
            h.notifyInbox(msgs);
         }
      }
   }
   
   private void pollView() 
   {
     // List<SmsgMessage> msgs = smsg
   }
   
   private void pollBuckets( ) {
      List<SmsgBucket> buckets = smsg.bucketStats().getBuckets();
      synchronized(bucketHandlers) 
      {
         for(ISmsgBucketHandler h : bucketHandlers) 
         {
            h.notifyBuckets(buckets);
         }
      }
   }
   
   private void schedule(Timer timer, TimerTask task, long frequencyMs) 
   {
      if(fixedTimeMode) 
      {
         timer.scheduleAtFixedRate(task, 0, frequencyMs);
      }
      else
      {
         timer.schedule(task, 0, frequencyMs);
      }
   }
}
