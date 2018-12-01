package org.particl.rpc;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;
import wf.bitcoin.javabitcoindrpcclient.BitcoinRPCException;


public class ParticlJSONRPCClient extends BitcoinJSONRPCClient implements ParticlRpcClient {

	private SMSG smsg = new ParticlSMSG();
	
	public ParticlJSONRPCClient(String host, String user, String password, int port) throws MalformedURLException
	  {
		  super(new URL("http://" + user + ':' + password + "@" + host + ":" +  Integer.toString(port) + "/"));
	  }

	@Override
	public SMSG getSMSG() {

		return smsg;
	}
	
	private class ParticlSMSG implements SMSG
	{

		@Override
		public void viewid(String msgId, SmsgOption... options) throws BitcoinRPCException {
			
			query(null);
		}

		@Override
		public boolean addaddress(String address, String pubKey) throws BitcoinRPCException {

			return false;
		}

		@Override
		public void addlocaladdress(String address) throws BitcoinRPCException {
			
			
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getpubkey(String address) throws BitcoinRPCException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<SmsgMessage> inbox(SmsgMode mode, String filter) throws BitcoinRPCException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean purge(String msgId) throws BitcoinRPCException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void scanbuckets() throws BitcoinRPCException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void scanchains() throws BitcoinRPCException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String send(String from, String to, String text) throws BitcoinRPCException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String sendanon(String to, String text) throws BitcoinRPCException {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
