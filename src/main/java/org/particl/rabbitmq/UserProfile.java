package org.particl.rabbitmq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.Connection;

class UserProfile implements Delayed {

   private long lastActivity = System.currentTimeMillis();
   
   private String particlAddress = null;
   private String pubkey = null;
   
   // The String required for the server to send a public key message to the requestor 
   private String pubkeySecret = null;
   
   private final Map<String, Connection> mqRoutes = new HashMap<String, Connection>();
   
   public UserProfile(String particlAddress) 
   {
      super();
      this.particlAddress = particlAddress;
   }
   
   public String getPubKey() 
   {
      return pubkey;
   }
   
   public String getParticlAddress()
   {
      return particlAddress;
   }
   
   public String getPubKeySecret() 
   {
      return this.pubkeySecret;
   }
   
   @Override
   public String toString() 
   {
      return "user profile address=" + particlAddress + "  # Routes=" + mqRoutes.size() + " lastActivity=" + (System.currentTimeMillis()-lastActivity) + " ms";
   }

   @Override
   public int compareTo(Delayed d) {

      return 0;
   }

   @Override
   public long getDelay(TimeUnit t) {

      return 0;
   }
   
   void updateLastActivity() 
   {
      lastActivity = System.currentTimeMillis();
   }
}
