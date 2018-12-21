package org.particl.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisPubSubTest {

   
   public static void main(String[] ra) throws InterruptedException
   {
      Jedis jPublisher = new Jedis("localhost");
      
      Thread t = new Thread() {
         @Override
         public void run() 
         {
            while(true) 
            {
               System.out.println("Sending");
               jPublisher.publish("E9873D79C6D87DC0FB6A5778633389_SAMPLE_PRIVATE_KEY_DO_NOT_IMPORT_F4453213303DA61F20BD67FC233AA33262", "test message");
               try {
                  Thread.sleep(100);
               } catch (InterruptedException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
            }
         }
         };
         
         t.start();
         

         Jedis jSubscriber = new Jedis("localhost");
         
         jSubscriber.subscribe(new JedisPubSub() {
             @Override
             public void onMessage(String channel, String message) {
                 

                System.out.println("Received message");
             }
         }, "E9873D79C6D87DC0FB6A5778633389_SAMPLE_PRIVATE_KEY_DO_NOT_IMPORT_F4453213303DA61F20BD67FC233AA33262");
         
         
         Thread.sleep(5000);
         
         
         
         
         Thread.sleep(10000);
      }
   }

