package org.particl.rabbitmq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.particl.rpc.core.IParticlCore.SMSG;
import org.particl.rpc.core.smsg.SmsgSendFailException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import wf.bitcoin.javabitcoindrpcclient.BitcoinRPCException;

public class ChannelResponseQueue implements Runnable {

   private final SMSG smsgCore;
   private final BlockingQueue<ChannelCreateResponse> responseQueue = new ArrayBlockingQueue<ChannelCreateResponse>(10);
   private String responseAddress = null;

   public ChannelResponseQueue(SMSG smsgCore, String responseAddress) {
      super();
      this.smsgCore = smsgCore;
      this.responseAddress = responseAddress;
   }

   public void sendResponse(ChannelCreateResponse response) {
      responseQueue.offer(response);
   }

   @Override
   public void run() {

      while (true) {
         ChannelCreateResponse response = null;
         try {

            response = responseQueue.take();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(response);

            smsgCore.send(responseAddress, response.getRequest().getRequestAddress(), json);

         } catch (InterruptedException | BitcoinRPCException | SmsgSendFailException e) {
            
            System.err.println(
                  "Error responsing to channel create request from " + response.getRequest().getRequestAddress());
         }
      }
   }

}
