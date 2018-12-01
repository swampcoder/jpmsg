package org.particl.rpc;

import java.util.List;

import wf.bitcoin.javabitcoindrpcclient.BitcoinRPCException;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient;

public interface ParticlRpcClient extends BitcoindRpcClient {

	
	public SMSG getSMSG();

	static enum SmsgOption 
	{
		Delete, SetRead, AsciiEncoding, HexEncoding;
	}
	
	static enum SmsgStat
	{
		Stats,
		Dump;
	}
	
	static enum SmsgMode
	{
		All,
		Unread,
		Clear;
	}
	
	/**
	 * Interfacing defining particl smsg functionality 
	 * @author mint
	 */
	public static interface SMSG
	{
		public void viewid(String msgId, SmsgOption...options) throws BitcoinRPCException;
		public boolean addaddress(String address, String pubKey) throws BitcoinRPCException;
		public void addlocaladdress(String address) throws BitcoinRPCException;
		public void buckets(SmsgStat stat) throws BitcoinRPCException;
		public void disable() throws BitcoinRPCException;
		public void enable(String walletName) throws BitcoinRPCException;
		public String getpubkey(String address) throws BitcoinRPCException;
		public List<SmsgMessage> inbox(SmsgMode mode, String filter) throws BitcoinRPCException;
		public boolean purge(String msgId) throws BitcoinRPCException;
		public void scanbuckets() throws BitcoinRPCException;
		public void scanchains() throws BitcoinRPCException;
		public String send(String from, String to, String text) throws BitcoinRPCException;
		public String sendanon(String to, String text) throws BitcoinRPCException;
	    // add smsgview 
		
	}
}
