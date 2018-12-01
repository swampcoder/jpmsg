package org.particl.rpc;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;
import wf.bitcoin.javabitcoindrpcclient.BitcoinRPCException;

public class ParticlJSONRPCClient extends BitcoinJSONRPCClient implements ParticlRpcClient {

   public static void main(String[] args) {
      try {
         ParticlJSONRPCClient rpc = new ParticlJSONRPCClient("localhost", "particl", "password", 51735);
         System.out.println("balance: " + rpc.getBalance());
         rpc.getSMSG().send("", "", "Msg");
         rpc.getSMSG().getpubkey("");
      } catch (MalformedURLException e) {
         e.printStackTrace();
      }
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

      @Override
      public void viewid(String msgId, SmsgOption... options) throws BitcoinRPCException {

         Object r = query("smsg", msgId);
         System.out.println("smsg = " + r);
      }

      @Override
      public boolean addaddress(String address, String pubKey) throws BitcoinRPCException {

         return false;
      }

      @Override
      public void addlocaladdress(String address) throws BitcoinRPCException {

         Object r = query("addlocaladdress", address);
         System.out.println("smsgaddlocaladdress = " + r);
      }

      @Override
      public void buckets(SmsgStat stat) throws BitcoinRPCException {

      }

      @Override
      public void disable() throws BitcoinRPCException {

         query(null);

      }

      @Override
      public void enable(String walletName) throws BitcoinRPCException {
         query("smsgenable", walletName);
      }

      @Override
      public String getpubkey(String address) throws BitcoinRPCException {
         Object r = query("smsggetpubkey", address);
         System.out.println("getpubkey " + r);
         return "pubkey";
      }

      @Override
      public List<SmsgMessage> inbox(SmsgMode mode, String filter) throws BitcoinRPCException {

         return null;
      }

      @Override
      public boolean purge(String msgId) throws BitcoinRPCException {
         Object r = query("smsgpurge", msgId);
         System.out.println("smsgpurge = " + r);
         return false;
      }

      @Override
      public void scanbuckets() throws BitcoinRPCException {

         Object r = query("smsgscanbuckets");
         System.out.println(r);
      }

      @Override
      public void scanchains() throws BitcoinRPCException {

         Object r = query("smsgscanchain");
         System.out.println(r);
      }

      @Override
      public String send(String from, String to, String text) throws BitcoinRPCException {
         Object response = query("smsgsend", from, to, text);
         System.out.println(response);
         return "bad";
      }

      @Override
      public String sendanon(String to, String text) throws BitcoinRPCException {

         Object response = query("smsgsendanon", to, text);
         System.out.println("smsgsendanon = " + response);
         return null;
      }

   }
}
