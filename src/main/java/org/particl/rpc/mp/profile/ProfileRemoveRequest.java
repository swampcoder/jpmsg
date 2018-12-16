package org.particl.rpc.mp.profile;

public class ProfileRemoveRequest {

   private Integer profileId = null;
   private String newProfileName = null;
   
   public ProfileRemoveRequest(int profileId) 
   {
      super();
   }
   
   public ProfileRemoveRequest(String newProfileName)
   {
      
   }

   public Integer getProfileId() {
      return profileId;
   }

   public String getNewProfileName() {
      return newProfileName;
   }
   
   
}
