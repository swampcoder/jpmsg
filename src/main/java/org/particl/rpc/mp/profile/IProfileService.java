package org.particl.rpc.mp.profile;

import java.util.List;

import org.particl.rpc.mp.dto.Profile;
import org.particl.rpc.mp.profile.address.IProfileAddressService;

public interface IProfileService {

   public List<Profile> list();
   
   public boolean add(String profileName, String profileAddress);
   
   public boolean update(Integer profileId, String newProfileName);
   
   public boolean remove(ProfileRemoveRequest request);
   
   public IProfileAddressService getProfileAddressService();
   
   
}
