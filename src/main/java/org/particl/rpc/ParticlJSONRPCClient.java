package org.particl.rpc;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
   
   public static void main(String[] args) {
      try {
         ParticlJSONRPCClient rpc = new ParticlJSONRPCClient("localhost", "particl", "password", 51735);
         System.out.println("balance: " + rpc.getBalance());
         
         rpc.getSMSG().disable();
         rpc.getSMSG().enable("wallet.dat");
         
         String rcvAddr1 = createReceiveAddress(rpc);
         String rcvAddr2 = createReceiveAddress(rpc);
         
         for(int i = 0; i < 100; i++) 
         {
        	
            rpc.getSMSG().send(rcvAddr2, rcvAddr1, "test message " + i);
         }
         //rpc.getSMSG().buckets(SmsgStat.Dump);
         rpc.getSMSG().buckets(SmsgStat.Stats);
         rpc.getSMSG().scanbuckets();
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

         Object response = query("smsg", msgId);
         System.out.println("smsg = " + response);
         SmsgMessage msg = new SmsgMessage(msgId);
         
         // parse/set
         
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
