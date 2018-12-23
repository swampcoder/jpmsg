package org.particl.ui.smsg;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import org.particl.rpc.core.ParticlJSONRPCClient;
import org.particl.rpc.core.smsg.SmsgPoller;

public class SmsgViewPanel extends JPanel {

   private final JTabbedPane smsgTabs = new JTabbedPane();
   private ParticlJSONRPCClient particl;
   private SmsgPoller poller = null; 
   
   // tables
   private SmsgKeyTable localKeyTable = null;
   private SmsgMessageTable inboxTable = null;
   private SmsgMessageTable outboxTable = null;
   private SmsgBucketTable bucketTable = null;
   
   
   public SmsgViewPanel(ParticlJSONRPCClient particl) 
   {
      super();
      
      this.particl = particl;
      this.poller = new SmsgPoller(false);
      
      
      localKeyTable = new SmsgKeyTable();
      inboxTable = new InboxSmsgTable(particl);
      outboxTable = new SmsgMessageTable(particl);
      bucketTable = new SmsgBucketTable();
      
      poller.addLocalKeyHandler(localKeyTable);
      poller.addInboxHandler(inboxTable);
      poller.addOutboxHandler(outboxTable);
      poller.addBucketHandler(bucketTable);
      
      
      this.poller.scheduleBuckets(5000);
      this.poller.scheduleInbox(5000L, null);
      this.poller.scheduleOutbox(5000L, null);
      this.poller.scheduleLocalKeys(10000);  
      
      smsgTabs.addTab("Inbox", new JScrollPane(inboxTable));
      smsgTabs.addTab("Outbox", new JScrollPane(outboxTable));
      smsgTabs.addTab("Buckets", new JScrollPane(bucketTable));
      smsgTabs.addTab("Keys", new JScrollPane(localKeyTable));
      //this.setLayout(new GridLayout(1,1));
     // add(smsgTabs);
      
      setLayout(new GridLayout(2,2));
      add(new JScrollPane(inboxTable));
      add(new JScrollPane(outboxTable));
      add( new JScrollPane(bucketTable));
      add(new JScrollPane(localKeyTable));
      
      

   }
}
