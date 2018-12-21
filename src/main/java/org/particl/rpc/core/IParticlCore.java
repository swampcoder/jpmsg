package org.particl.rpc.core;

import java.util.List;
import java.util.Map;

import org.particl.rpc.core.smsg.SmsgBucketStats;
import org.particl.rpc.core.smsg.SmsgKey;
import org.particl.rpc.core.smsg.SmsgMessage;
import org.particl.rpc.core.smsg.SmsgMessageSendResult;
import org.particl.rpc.core.smsg.SmsgSendFailException;
import org.particl.rpc.mp.dto.Bid;
import org.particl.rpc.mp.dto.CurrencyPrice;
import org.particl.rpc.mp.dto.DSNProtocol;
import org.particl.rpc.mp.dto.EscrowType;
import org.particl.rpc.mp.dto.Item;
import org.particl.rpc.mp.dto.ItemCategory;
import org.particl.rpc.mp.dto.ItemImage;
import org.particl.rpc.mp.dto.ItemInformation;
import org.particl.rpc.mp.dto.ItemType;
import org.particl.rpc.mp.dto.Market;
import org.particl.rpc.mp.dto.ModifyType;
import org.particl.rpc.mp.dto.OrderItem;
import org.particl.rpc.mp.dto.OrderStatus;
import org.particl.rpc.mp.dto.Ordering;
import org.particl.rpc.mp.dto.PriceTicker;
import org.particl.rpc.mp.dto.Profile;
import org.particl.rpc.mp.dto.ProfileAddress;
import org.particl.rpc.mp.dto.ProfileFavorite;
import org.particl.rpc.mp.dto.ShippingAvailability;
import org.particl.rpc.mp.dto.ShippingDestination;
import org.particl.rpc.mp.dto.ShoppingCart;

import wf.bitcoin.javabitcoindrpcclient.BitcoinRPCException;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient;

public interface IParticlCore extends BitcoindRpcClient {

   
   public SMSG getSMSG();
   
   public static enum SmsgOption {
      Delete, SetRead, AsciiEncoding, HexEncoding;
   }

   public static enum SmsgInboxMode {
      All, Unread, Clear;
   }
   
   public static enum SmsgOutboxMode
   {
	   All, Clear;
   }
   
   public static enum SmsgLocation
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
