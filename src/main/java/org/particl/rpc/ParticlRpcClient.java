package org.particl.rpc;

import java.util.List;

import org.particl.rpc.SmsgSendFailException;
import wf.bitcoin.javabitcoindrpcclient.BitcoinRPCException;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient;

public interface ParticlRpcClient extends BitcoindRpcClient {

   public SMSG getSMSG();

   static enum SmsgOption {
      Delete, SetRead, AsciiEncoding, HexEncoding;
   }

   static enum SmsgInboxMode {
      All, Unread, Clear;
   }
   
   static enum SmsgOutboxMode
   {
	   All, Clear;
   }
   
   static enum SmsgLocation
   {
	   Inbox, Outbox, Sending;
   }

   /**
    * Interfacing defining particl smsg functionality
    * 
    * @author mint
    */
   public static interface SMSG {
	   
      public SmsgMessage viewid(String msgId, SmsgOption... options) throws BitcoinRPCException;

      public boolean addaddress(String address, String pubKey) throws BitcoinRPCException;

      public boolean addlocaladdress(String address) throws BitcoinRPCException;

      public SmsgBucketStats bucketStats( ) throws BitcoinRPCException;
      
      public boolean bucketDump () throws BitcoinRPCException;

      public boolean disable() throws BitcoinRPCException;

      public boolean enable(String walletName) throws BitcoinRPCException;

      public String getpubkey(String address) throws BitcoinRPCException;
      
      public boolean importprivkey(String privKey, String label) throws BitcoinRPCException;
      
      public List<SmsgKey> smsgKeys() throws BitcoinRPCException;

      public List<SmsgMessage> inbox(SmsgInboxMode mode, String filter) throws BitcoinRPCException;
      
      public List<SmsgMessage> outbox(SmsgOutboxMode mode, String filter) throws BitcoinRPCException;

      public boolean purge(String msgId) throws BitcoinRPCException;

      public boolean scanbuckets() throws BitcoinRPCException;

      public boolean scanchain() throws BitcoinRPCException;

      public SmsgMessageSendResult send(String from, String to, String text) throws BitcoinRPCException, SmsgSendFailException;

      //public String sendanon(String to, String text) throws BitcoinRPCException;
      // add smsgview

   }
}
