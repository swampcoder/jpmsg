package org.particl.rabbitmq;

import java.util.HashMap;
import java.util.Map;

public class UserProfileCache {

   // K=ParticlAddress V=Profile
   private final Map<String, UserProfile> users = new HashMap<String, UserProfile>();
   
   
   public UserProfileCache() 
   {
      super();
   }
   
   void storeUser(UserProfile user) 
   {
      UserProfile existing = users.put(user.getParticlAddress(),user);
   }
}
