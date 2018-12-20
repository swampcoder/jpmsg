package org.particl.rabbitmq;

import java.util.concurrent.DelayQueue;

public class UserExpirationQueue implements Runnable {

   private final DelayQueue<UserProfile> expirationQueue = new DelayQueue<UserProfile>();
   
   public UserExpirationQueue() 
   {
      super();
   }
   
   @Override
   public void run() {

      UserProfile expiredUser;
      try {
         expiredUser = expirationQueue.take();
         expireUser(expiredUser);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }
   
   
   private void expireUser(UserProfile user) 
   {
      
      // TODO
   }
}
