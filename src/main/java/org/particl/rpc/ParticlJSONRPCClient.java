package org.particl.rpc;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
   static
   {
	   LocationMap.put("inbox", SmsgLocation.Inbox);
	   LocationMap.put("outbox",  SmsgLocation.Outbox);
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
         for(int i = 0; i < 10; i++) 
         {
        	 SmsgMessageSendResult result = rpc.getSMSG().send(rcvAddr2, rcvAddr1, "test message " + i);
        	 results.add(result);
         }
         //rpc.getSMSG().buckets(SmsgStat.Dump);
         rpc.getSMSG().buckets(SmsgStat.Stats);
         rpc.getSMSG().scanbuckets();
         
         for(SmsgMessageSendResult result : results)
         {
        	 SmsgMessage msg = rpc.getSMSG().viewid(result.getMsgId());
        	 System.out.println(msg);
         }
         //rpc.getSMSG().scanchain();
         
      } catch (MalformedURLException | BitcoinRPCException | SmsgSendFailException e) {
         e.printStackTrace();
      }
   }
   
   private static String createReceiveAddress(ParticlJSONRPCClient rpc) 
   {
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

	  private ParticlSMSG() {}
	  
      @Override
      public SmsgMessage viewid(String msgId, SmsgOption... options) throws BitcoinRPCException {
    	  
    	 LinkedHashMap response = (LinkedHashMap) query("smsg", msgId);
    	 
         SmsgMessage msg = new SmsgMessage(msgId);
         int version = Integer.parseInt((String) response.get("version"));
         long receivedTime = (Long) response.get("received");
         String toAddr = (String) response.get("to");
         String fromAddr = (String) response.get("from");
         boolean msgRead = (Boolean) "true".equals(response.get("read"));
         boolean msgPaid = (Boolean) "true".equals(response.get("paid"));
         Long expiration = (Long) response.get("expiration");
         String msgText = (String) response.get("text");
         
         msg.setVersion(version);
         msg.setReceiveTime(receivedTime);
         msg.setToAddress(toAddr);
         msg.setFromAddress(fromAddr);
         msg.setMsgRead(msgRead);
         msg.setExpiryTime(expiration);
         msg.setMsgLocation(LocationMap.get(response.get("location")));
         System.out.println(msg);
         return msg;
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
      public boolean buckets(SmsgStat stat) throws BitcoinRPCException {

    	  Object response = query("smsgbuckets", stat.name().toLowerCase());
    	  System.out.println("smsgbuckets " + response);
    	  return true;
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
      public List<SmsgMessage> inbox(SmsgMode mode, String filter) throws BitcoinRPCException {
         Object response = query("smsginbox", mode.name().toLowerCase(), filter);
         System.out.println("smsginbox = " + response);
         List<SmsgMessage> messages = new ArrayList<SmsgMessage>();
         
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
      public SmsgMessageSendResult send(String from, String to, String text) throws BitcoinRPCException, SmsgSendFailException {
    	 LinkedHashMap response = (LinkedHashMap) query("smsgsend", from, to, text);
    	 if(SendFailOuptut.equals(response.get("result")))
    	 {
    		 throw new SmsgSendFailException((String) response.get("error"));
    	 }
    	 String msgId = (String) response.get("msgid");
    	 System.out.println(response);
    	 return new SmsgMessageSendResult(msgId);
      }

      /*@Override
      public String sendanon(String to, String text) throws BitcoinRPCException {

         Object response = query("smsgsendanon", to, text);
         System.out.println("smsgsendanon = " + response);
         return null;
      }*/

   }
}
