package org.particl.rabbitmq;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.codec.binary.Base64;
import org.particl.rpc.core.IParticlCore.SMSG;
import org.particl.rpc.core.ParticlJSONRPCClient;
import org.particl.rpc.core.smsg.SmsgSendFailException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.ConnectionFactory;

import wf.bitcoin.javabitcoindrpcclient.BitcoinRPCException;

public class ParticlSmsgNode {

   private static final ExecutorService QueueThreads = Executors.newFixedThreadPool(2);
   
   private final UserProfileCache userCache = new UserProfileCache();
   
   private ParticlJSONRPCClient particlRPC = null;
   private ParticlRequestMonitorThread smsgInboxMonitor = null;
   private ChannelRequestQueue requestQueue = null;
   private ChannelResponseQueue responseQueue = null;
   
   private String address = null;
   
   public ParticlSmsgNode( ) throws MalformedURLException 
   {
      super();
      particlRPC = new ParticlJSONRPCClient("localhost", "particl", "password", 22222);
   }
   
   String[] start(String rabbitMQuri) throws KeyManagementException, NoSuchAlgorithmException, URISyntaxException 
   {
      //particlRPC.getSMSG().enable("wallet.dat");
      String newReceiveAddress = particlRPC.getNewAddress();
      String pubkey = particlRPC.getSMSG().getpubkey(newReceiveAddress);
      boolean result = particlRPC.getSMSG().addaddress(newReceiveAddress, pubkey);
      result = result && particlRPC.getSMSG().addlocaladdress(newReceiveAddress);

      System.out.println("Generated address=" + newReceiveAddress);
      System.out.println("Generated pubkey=" + pubkey);
      System.out.println("Initialized wallet=" + result);
      address = newReceiveAddress;
      if(!result) return null;
      
      ConnectionFactory mqfactory = new ConnectionFactory();
      mqfactory.setHost("localhost");
      //mqfactory.setUri(rabbitMqUri);

      responseQueue = new ChannelResponseQueue(particlRPC.getSMSG(), address);
      requestQueue = new ChannelRequestQueue(mqfactory, responseQueue, userCache);
      
      QueueThreads.execute(responseQueue);
      QueueThreads.execute(requestQueue);

      smsgInboxMonitor = new ParticlRequestMonitorThread(particlRPC.getSMSG(), requestQueue);
      
      return new String[] { address, pubkey} ;
   }
   
   public static void main(String[] args) 
   {
      String mqURI = "amqp://guest:guest@localhost/5672";
      ParticlSmsgNode node;
      try {
         node = new ParticlSmsgNode();
         
         String[] addr_pk = node.start(mqURI);
         if(addr_pk == null) 
         {
            System.out.println("SMSG initialization failed. Exiting");
            System.exit(1);
         }
         else
         {
            System.out.println("PARTICL SMSG NODE INITIALIZED WITH PUBKEY " + addr_pk[1]);
            
            runTester(addr_pk[0], node.particlRPC.getSMSG());
         }
      } catch (MalformedURLException | KeyManagementException | NoSuchAlgorithmException | URISyntaxException e) {
         e.printStackTrace();
      }   
   }
   
   private static long requestId = 0L;
   private static String testFrom = "pXcuKw1spvJJ6w34aAQLLeqCBcj4anATtB";
   private static void runTester(String address, SMSG smsg) 
   {
      
      Timer t = new Timer();
      t.schedule(new TimerTask() {
         
         @Override
         public void run() 
         {
            requestId++;
            ChannelCreateRequest request = new ChannelCreateRequest("piX74JGWngpR6xbg3a7eKZZqwegmR7Ywdy", requestId);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(request);
            System.out.println("json: " + json);
            String encoded = Base64.encodeBase64String(json.getBytes());
            System.out.println("json64: " + encoded);
            try {
               smsg.send(testFrom, address, encoded);
            } catch (BitcoinRPCException | SmsgSendFailException e) {
               e.printStackTrace();
            }
         }
      }, 1000, 1500);
   }
}
