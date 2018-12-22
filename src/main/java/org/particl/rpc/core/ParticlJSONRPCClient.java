package org.particl.rpc.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.particl.rpc.core.smsg.ParticlSMSG;
import org.particl.rpc.core.smsg.SmsgBucket;
import org.particl.rpc.core.smsg.SmsgBucketStats;
import org.particl.rpc.core.smsg.SmsgInboxMode;
import org.particl.rpc.core.smsg.SmsgKey;
import org.particl.rpc.core.smsg.SmsgMessage;
import org.particl.rpc.core.smsg.SmsgMessageSendResult;
import org.particl.rpc.core.smsg.SmsgOutboxMode;
import org.particl.rpc.core.smsg.SmsgSendFailException;

import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;

public class ParticlJSONRPCClient extends BitcoinJSONRPCClient implements IParticlCore {

   public static void main(String[] args) {

      try {

         ParticlJSONRPCClient rpc = new ParticlJSONRPCClient("localhost", "particl", "password", 22223);
         System.out.println("balance: " + rpc.getBalance());

         //rpc.getSMSG().enable("wallet.dat");
        // rpc.getSMSG().enable("dkd");
        // rpc.getSMSG().disable();
        // rpc.getSMSG().disable();
       //  rpc.getSMSG().enable("wallet.dat");

         String rcvAddr1 = createReceiveAddress(rpc);
         String rcvAddr2 = createReceiveAddress(rpc);

         List<SmsgMessageSendResult> results = new ArrayList<SmsgMessageSendResult>();
         for (int i = 0; i < 50; i++) {
            SmsgMessageSendResult result = rpc.getSMSG().send(rcvAddr2, rcvAddr1, "test message " + i);
            results.add(result);
         }
         // rpc.getSMSG().buckets(SmsgStat.Dump);
         SmsgBucketStats stats = rpc.getSMSG().bucketStats();
         System.out.println(stats);
         rpc.getSMSG().scanbuckets();

         for (SmsgMessageSendResult result : results) {
            SmsgMessage msg = rpc.getSMSG().viewid(result.getMsgId());
            System.out.println(msg);
            rpc.getSMSG().purge(msg.getMsgId());

         }
         List<SmsgMessage> inbox = rpc.getSMSG().inbox(SmsgInboxMode.All, "message 4");
         System.out.println(inbox);
         // rpc.getSMSG().scanchain();
         rpc.getSMSG().bucketStats();
         rpc.getSMSG().bucketDump();
         List<SmsgKey> smsgKeys = rpc.getSMSG().smsgKeys();
         System.out.println(smsgKeys);

         List<SmsgMessage> outbox = rpc.getSMSG().outbox(SmsgOutboxMode.All, null);

         System.out.println("Outbox size: " + outbox.size());

         String privKey = rpc.dumpPrivKey(rcvAddr2);
         boolean imported = rpc.getSMSG().importprivkey(privKey, null);
         System.out.println("private key imported: " + imported);
         imported = rpc.getSMSG().importprivkey("badkeyformat", null);
         System.out.println("private key imported (bad): " + imported);

         rpc.getSMSG().bucketStats();
         
         
         
         
 
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   private static String createReceiveAddress(ParticlJSONRPCClient rpc) {
      String newReceiveAddress = rpc.getNewAddress();
      System.out.println("new receive address: " + newReceiveAddress);
      String pubkey = rpc.getSMSG().getpubkey(newReceiveAddress);
      boolean result = rpc.getSMSG().addaddress(newReceiveAddress, pubkey);
      result = result && rpc.getSMSG().addlocaladdress(newReceiveAddress);
      System.out.println("Address added: " + result);
      return newReceiveAddress;
   }

   private SMSG smsg = new ParticlSMSG(this);

   // private MARKET market = new ParticlMARKET();

   public ParticlJSONRPCClient(String host, String user, String password, int port) throws MalformedURLException {
      super(new URL("http://" + user + ':' + password + "@" + host + ":" + Integer.toString(port)));
   }

   @Override
   public SMSG getSMSG() {

      return smsg;
   }

}
