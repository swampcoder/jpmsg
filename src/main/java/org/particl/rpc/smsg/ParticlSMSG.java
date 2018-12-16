package org.particl.rpc.smsg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.particl.rpc.core.ParticlJSONRPCClient;
import org.particl.rpc.core.IParticlCore.SMSG;
import org.particl.rpc.core.IParticlCore.SmsgInboxMode;
import org.particl.rpc.core.IParticlCore.SmsgLocation;
import org.particl.rpc.core.IParticlCore.SmsgOption;
import org.particl.rpc.core.IParticlCore.SmsgOutboxMode;

import wf.bitcoin.javabitcoindrpcclient.BitcoinRPCException;

public class ParticlSMSG implements SMSG {
   
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

   private final ParticlJSONRPCClient client;

      public ParticlSMSG(ParticlJSONRPCClient client) {
         this.client = client;
      }

      @SuppressWarnings("rawtypes")
      @Override
      public SmsgMessage viewid(String msgId, SmsgOption... options) throws BitcoinRPCException {

         LinkedHashMap response = (LinkedHashMap) client.query("smsg", msgId);
         return parseSmsgMsg(response);
      }

      @SuppressWarnings("rawtypes")
      @Override
      public boolean addaddress(String address, String pubKey) throws BitcoinRPCException {

         LinkedHashMap response = (LinkedHashMap) client.query("smsgaddaddress", address, pubKey);
         return AddAddressOutput.equals(response.get("result"));
      }

      @SuppressWarnings("rawtypes")
      @Override
      public boolean addlocaladdress(String address) throws BitcoinRPCException {

         LinkedHashMap response = (LinkedHashMap) client.query("smsgaddlocaladdress", address);
         return AddLocalAddressOutput.equals(response.get("result"));
      }

      @SuppressWarnings("rawtypes")
      @Override
      public SmsgBucketStats bucketStats() throws BitcoinRPCException {

         LinkedHashMap response = (LinkedHashMap) client.query("smsgbuckets", "stats");
         System.out.println("response: " + response);
         SmsgBucketStats bucketStats = new SmsgBucketStats();
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
               }
            } else if (k.equals("total")) {
               // numbuckets=1, numpurged=52, messages=5, size=1.05 KB
               LinkedHashMap map = (LinkedHashMap) response.get(k);
               Long numBuckets = (Long) map.get("numbuckets");
               Long numPurged = (Long) map.get("numpurged");
               Long messages = (Long) map.get("messages");
               String size = (String) map.get("size");
               bucketStats.setNumBuckets(numBuckets.intValue());
               bucketStats.setNumMessages(messages.intValue());
               bucketStats.setNumPurged(numPurged.intValue());
            }
         }
         return bucketStats;
      }

      @SuppressWarnings("rawtypes")
      @Override
      public boolean bucketDump() throws BitcoinRPCException {
         LinkedHashMap response = (LinkedHashMap) client.query("smsgbuckets", "dump");
         return BucketDumpOutput.equals(response.get("result"));
      }

      @SuppressWarnings("rawtypes")
      @Override
      public boolean disable() throws BitcoinRPCException {

         try
         {
            LinkedHashMap response = (LinkedHashMap) client.query("smsgdisable");
            return DisabledOutput.equals(response.get("result"));
         }
         catch(BitcoinRPCException rpcException)
         {
            System.out.println(rpcException);
         }
         return false;
      }

      @SuppressWarnings("rawtypes")
      @Override
      public boolean enable(String walletName) throws BitcoinRPCException {
         try
         {
            LinkedHashMap response = (LinkedHashMap) client.query("smsgenable", walletName);
            return EnabledOutput.equals(response.get("result"));
         } catch(BitcoinRPCException rpcException)
         {
            System.out.println(rpcException);
         }
         return false;
      }

      @SuppressWarnings("rawtypes")
      @Override
      public String getpubkey(String address) throws BitcoinRPCException {
         LinkedHashMap response = (LinkedHashMap) client.query("smsggetpubkey", address);
         return (String) response.get("publickey");
      }

      @Override
      public boolean importprivkey(String privKey, String label) throws BitcoinRPCException {
         try {
            client.query("smsgimportprivkey", privKey, label);
            return true;
         } catch (Exception e) {
            return false;
         }
      }

      @SuppressWarnings("rawtypes")
      @Override
      public List<SmsgKey> smsgKeys() throws BitcoinRPCException {
         LinkedHashMap response = (LinkedHashMap) client.query("smsglocalkeys");
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
         LinkedHashMap response = (LinkedHashMap) client.query("smsginbox", mode.name().toLowerCase(), filter);
         System.out.println("smsginbox = " + response);
         List<SmsgMessage> messages = new ArrayList<SmsgMessage>();
         List msgs = (List) response.get("messages");
         for (Object msg : msgs) {
            LinkedHashMap map = (LinkedHashMap) msg;
            messages.add(parseSmsgMsg(map));
         }
         return messages;
      }

      @SuppressWarnings("rawtypes")
      @Override
      public List<SmsgMessage> outbox(SmsgOutboxMode mode, String filter) throws BitcoinRPCException {
         LinkedHashMap response = (LinkedHashMap) client.query("smsgoutbox", mode.name().toLowerCase(), filter);
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
         Object response = client.query("smsgpurge", msgId);
         System.out.println("smsgpurge = " + response);
         return false;
      }

      @SuppressWarnings("rawtypes")
      @Override
      public boolean scanbuckets() throws BitcoinRPCException {

         LinkedHashMap response = (LinkedHashMap) client.query("smsgscanbuckets");
         System.out.println(response);
         return ScanBucketsOutput.equals(response.get("result"));
      }

      @SuppressWarnings("rawtypes")
      @Override
      public boolean scanchain() throws BitcoinRPCException {

         LinkedHashMap response = (LinkedHashMap) client.query("smsgscanchain");
         System.out.println(response);
         return ScanChainOutput.equals(response.get("result"));
      }

      @SuppressWarnings("rawtypes")
      @Override
      public SmsgMessageSendResult send(String from, String to, String text)
            throws BitcoinRPCException, SmsgSendFailException {
         LinkedHashMap response = (LinkedHashMap) client.query("smsgsend", from, to, text);
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
         Long daysRetention = (Long) map.get("daysRetention");

         msg.setVersion(version);
         msg.setReceiveTime(received);
         msg.setSentTime(sent);
         msg.setToAddress(toAddr);
         msg.setFromAddress(fromAddr);
         msg.setMsgRead(msgRead);
         msg.setExpiryTime(expiration);
         msg.setMsgText(msgText);
         msg.setMsgPaid(msgPaid);
         if (daysRetention != null) {
            msg.setDaysRetention(daysRetention.intValue());
         }
         msg.setMsgLocation(LocationMap.get(map.get("location")));
         System.out.println(msg);

         return msg;
      }
   }