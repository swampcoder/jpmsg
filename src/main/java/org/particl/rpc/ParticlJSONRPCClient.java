package org.particl.rpc;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;
import wf.bitcoin.javabitcoindrpcclient.BitcoinRPCException;

public class ParticlJSONRPCClient extends BitcoinJSONRPCClient implements ParticlRpcClient {

   private final static String DisabledOutput = "Disabled secure messaging.";
   private final static String EnabledOutput = "Enabled secure messaging.";
   private final static String AddAddressOutput = "Public key added to db.";
   private final static String AddLocalAddressOutput = "Receiving messages enabled for address.";
   private final static String BucketDumpOutput = "Removed all buckets.";
   private final static String ScanBucketsOutput = "Scan Buckets Completed.";
   private final static String ScanChainOutput = "Scan Chain Completed.";
   private final static String SendFailOuptut = "Send failed.";

   private final static Map<String, SmsgLocation> LocationMap = new HashMap<String, SmsgLocation>();
   static {
      LocationMap.put("inbox", SmsgLocation.Inbox);
      LocationMap.put("outbox", SmsgLocation.Outbox);
      LocationMap.put("sending", SmsgLocation.Sending);
   }

   public static void main(String[] args) {
      try {
         ParticlJSONRPCClient rpc = new ParticlJSONRPCClient("localhost", "particl", "password", 51735);
         System.out.println("balance: " + rpc.getBalance());

         rpc.getSMSG().disable();
         rpc.getSMSG().enable("wallet.dat");

         String rcvAddr1 = createReceiveAddress(rpc);
         String rcvAddr2 = createReceiveAddress(rpc);

         List<SmsgMessageSendResult> results = new ArrayList<SmsgMessageSendResult>();
         for (int i = 0; i < 5; i++) {
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

      } catch (MalformedURLException | BitcoinRPCException | SmsgSendFailException e) {
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

   private SMSG smsg = new ParticlSMSG();

   public ParticlJSONRPCClient(String host, String user, String password, int port) throws MalformedURLException {
      super(new URL("http://" + user + ':' + password + "@" + host + ":" + Integer.toString(port) + "/"));
   }

   @Override
   public SMSG getSMSG() {

      return smsg;
   }

   private class ParticlSMSG implements SMSG {

      private ParticlSMSG() {
      }

      @Override
      public SmsgMessage viewid(String msgId, SmsgOption... options) throws BitcoinRPCException {

         LinkedHashMap response = (LinkedHashMap) query("smsg", msgId);
         return parseSmsgMsg(response);
      }

      @SuppressWarnings("rawtypes")
      @Override
      public boolean addaddress(String address, String pubKey) throws BitcoinRPCException {

         LinkedHashMap response = (LinkedHashMap) query("smsgaddaddress", address, pubKey);
         return AddAddressOutput.equals(response.get("result"));
      }

      @SuppressWarnings("rawtypes")
      @Override
      public boolean addlocaladdress(String address) throws BitcoinRPCException {

         LinkedHashMap response = (LinkedHashMap) query("smsgaddlocaladdress", address);
         return AddLocalAddressOutput.equals(response.get("result"));
      }

      @Override
      public SmsgBucketStats bucketStats() throws BitcoinRPCException {

         LinkedHashMap response = (LinkedHashMap) query("smsgbuckets", "stats");
         System.out.println("response: " + response);
         SmsgBucketStats bucketStats = new SmsgBucketStats();
         List<SmsgBucket> buckets = new ArrayList<SmsgBucket>();
         Iterator i = response.keySet().iterator();
         while (i.hasNext()) {
            String k = (String) i.next();
            if (k.equals("buckets")) {
               List values = (List) response.get(k);
               for (Object v : values) {
                  LinkedHashMap map = (LinkedHashMap) v;
                  SmsgBucket bucket = new SmsgBucket(Long.parseLong((String) map.get("bucket")));
                  bucket.setTime((Long) map.get("time"));
                  bucket.setNumMessages(Integer.parseInt((String) map.get("no. messages")));
                  bucket.setActiveMessages(Integer.parseInt((String) map.get("active messages")));
                  bucket.setHash(Long.parseLong((String) map.get("hash")));
                  bucket.setLastChangeTime((String) map.get("last changed"));

                  bucketStats.addBucket(bucket);
                  // bucket.setBucketSizeKb(bucketSizeKb);
                  // i = bucket.keySet().iterator();
                  /*
                   * while(i.hasNext()) { String k2 = (String) i.next();
                   * 
                   * System.out.println("key=" + k2 + "   value=" + map.get(k2) + "   class=" +
                   * map.get(k2).getClass()); }
                   */
               }
            } else if (k.equals("total")) {

            }

            System.out.println("keyv: " + k);
            System.out.println("keycls: " + k.getClass());
            ;
            System.out.println("valuecls: " + response.get(k).getClass());
         }
         return bucketStats;
      }

      @SuppressWarnings("rawtypes")
      @Override
      public boolean bucketDump() throws BitcoinRPCException {
         LinkedHashMap response = (LinkedHashMap) query("smsgbuckets", "dump");
         return BucketDumpOutput.equals(response.get("result"));
      }

      @SuppressWarnings("rawtypes")
      @Override
      public boolean disable() throws BitcoinRPCException {

         LinkedHashMap response = (LinkedHashMap) query("smsgdisable");
         return DisabledOutput.equals(response.get("result"));
      }

      @SuppressWarnings("rawtypes")
      @Override
      public boolean enable(String walletName) throws BitcoinRPCException {
         LinkedHashMap response = (LinkedHashMap) query("smsgenable", walletName);
         return EnabledOutput.equals(response.get("result"));
      }

      @SuppressWarnings("rawtypes")
      @Override
      public String getpubkey(String address) throws BitcoinRPCException {
         LinkedHashMap response = (LinkedHashMap) query("smsggetpubkey", address);
         return (String) response.get("publickey");
      }

      @Override
      public boolean importprivkey(String privKey, String label) throws BitcoinRPCException {
         try {
            query("smsgimportprivkey", privKey, label);
            return true;
         } catch (Exception e) {
            return false;
         }
      }

      @SuppressWarnings("rawtypes")
      @Override
      public List<SmsgKey> smsgKeys() throws BitcoinRPCException {
         LinkedHashMap response = (LinkedHashMap) query("smsglocalkeys");
         List<SmsgKey> keys = new ArrayList<SmsgKey>();
         List keyList = (List) response.get("smsg_keys");
         for (Object o : keyList) {
            LinkedHashMap map = (LinkedHashMap) o;
            SmsgKey smsgKey = new SmsgKey();
            smsgKey.setAddress((String) map.get("address"));
            smsgKey.setPublicKey((String) map.get("public_key"));
            smsgKey.setLabel((String) map.get("label"));
            keys.add(smsgKey);
         }
         return keys;
      }

      @SuppressWarnings("rawtypes")
      @Override
      public List<SmsgMessage> inbox(SmsgInboxMode mode, String filter) throws BitcoinRPCException {
         LinkedHashMap response = (LinkedHashMap) query("smsginbox", mode.name().toLowerCase(), filter);
         System.out.println("smsginbox = " + response);
         List<SmsgMessage> messages = new ArrayList<SmsgMessage>();
         List msgs = (List) response.get("messages");
         for (Object msg : msgs) {
            LinkedHashMap map = (LinkedHashMap) msg;
            messages.add(parseSmsgMsg(map));
         }
         return messages;
      }

      public List<SmsgMessage> outbox(SmsgOutboxMode mode, String filter) throws BitcoinRPCException {
         LinkedHashMap response = (LinkedHashMap) query("smsgoutbox", mode.name().toLowerCase(), filter);
         System.out.println("smsgoutbox = " + response);
         List<SmsgMessage> messages = new ArrayList<SmsgMessage>();
         List msgs = (List) response.get("messages");
         for (Object msg : msgs) {
            LinkedHashMap map = (LinkedHashMap) msg;
            messages.add(parseSmsgMsg(map));
         }
         return messages;
      }

      @Override
      public boolean purge(String msgId) throws BitcoinRPCException {
         Object response = query("smsgpurge", msgId);
         System.out.println("smsgpurge = " + response);
         return false;
      }

      @SuppressWarnings("rawtypes")
      @Override
      public boolean scanbuckets() throws BitcoinRPCException {

         LinkedHashMap response = (LinkedHashMap) query("smsgscanbuckets");
         System.out.println(response);
         return ScanBucketsOutput.equals(response.get("result"));
      }

      @SuppressWarnings("rawtypes")
      @Override
      public boolean scanchain() throws BitcoinRPCException {

         LinkedHashMap response = (LinkedHashMap) query("smsgscanchain");
         System.out.println(response);
         return ScanChainOutput.equals(response.get("result"));
      }

      @SuppressWarnings("rawtypes")
      @Override
      public SmsgMessageSendResult send(String from, String to, String text)
            throws BitcoinRPCException, SmsgSendFailException {
         LinkedHashMap response = (LinkedHashMap) query("smsgsend", from, to, text);
         if (SendFailOuptut.equals(response.get("result"))) {
            throw new SmsgSendFailException((String) response.get("error"));
         }
         String msgId = (String) response.get("msgid");
         System.out.println(response);
         return new SmsgMessageSendResult(msgId);
      }

      @SuppressWarnings("rawtypes")
      private SmsgMessage parseSmsgMsg(LinkedHashMap map) {
         System.out.println(map);
         SmsgMessage msg = new SmsgMessage((String) map.get("msgid"));
         int version = Integer.parseInt((String) map.get("version"));
         Long received = (Long) map.get("received");
         Long sent = (Long) map.get("sent");
         String toAddr = (String) map.get("to");
         String fromAddr = (String) map.get("from");
         boolean msgRead = (Boolean) "true".equals(map.get("read"));
         boolean msgPaid = (Boolean) "true".equals(map.get("paid"));
         Long expiration = (Long) map.get("expiration");
         String msgText = (String) map.get("text");

         msg.setVersion(version);
         msg.setReceiveTime(received);
         msg.setSentTime(sent);
         msg.setToAddress(toAddr);
         msg.setFromAddress(fromAddr);
         msg.setMsgRead(msgRead);
         msg.setExpiryTime(expiration);
         msg.setMsgLocation(LocationMap.get(map.get("location")));
         System.out.println(msg);

         return msg;
      }

      /*
       * @Override public String sendanon(String to, String text) throws
       * BitcoinRPCException {
       * 
       * Object response = query("smsgsendanon", to, text);
       * System.out.println("smsgsendanon = " + response); return null; }
       */

   }
}
