package org.particl.rabbitmq;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ChannelRequestQueue implements Runnable {

   private final UserProfileCache userCache;
   private ConnectionFactory mqFactory = null;
   private final BlockingQueue<ChannelCreateRequest> requests = new ArrayBlockingQueue<ChannelCreateRequest>(10);
   private final ChannelResponseQueue responseQueue;
   
   private Connection mqConnection = null;
   
   public ChannelRequestQueue(ConnectionFactory mqFactory, ChannelResponseQueue responseQueue, UserProfileCache userCache) 
   {
      this.mqFactory = mqFactory;
      this.responseQueue = responseQueue;
      this.userCache = userCache;
   }
   
   public void handleRequest(ChannelCreateRequest request)
   {
      
      if(!requests.offer(request)) 
      {
         responseQueue.sendResponse(new ChannelCreateResponse(request)); // notify failures 
      }
   }
   
   private Connection initConnection() 
   {
      try {
         return mqFactory.newConnection();
      } catch (IOException | TimeoutException e1) {
         e1.printStackTrace();
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            // no throw
         }
      }
      return null;
   }

   @Override
   public void run() {
      
      while(true) 
      {
         
        mqConnection = initConnection();
        
        if(mqConnection == null) continue;
         
         while(true) 
         {
            ChannelCreateRequest request = null;
            ChannelCreateResponse response = null;
            Channel channel = null;
            try {
               
               request = requests.take();
               System.out.println("PROCESSING REQUEST ON QUEUE: " + request);
               response = new ChannelCreateResponse(request);
               String exchangeName = UUID.randomUUID().toString();
               channel = mqConnection.createChannel();
               channel.exchangeDeclare(exchangeName, "direct", true);
               String queueName = channel.queueDeclare().getQueue();
               String routingKey = UUID.randomUUID().toString();
               channel.queueBind(queueName, exchangeName, routingKey);
               
               UserProfile user = new UserProfile(request.getRequestAddress());
               userCache.storeUser(user);
               
               response.setResponseData("localhost", 5672, queueName, routingKey, user.getPubKeySecret());
               
               // notify channel details
               responseQueue.sendResponse(response);
              
  
            } catch (InterruptedException | IOException e) {
               // notify failure 
               responseQueue.sendResponse(response);
            }
            finally
            {
               if(channel != null)
               {
                  try {
                     channel.close();
                  } catch (IOException | TimeoutException e) {
                     e.printStackTrace();
                  }
               }
               
              
            }
         }  
      }
   }
}
