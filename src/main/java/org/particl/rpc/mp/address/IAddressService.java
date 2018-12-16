package org.particl.rpc.mp.address;

import java.util.List;

import org.particl.rpc.mp.dto.ProfileAddress;

public interface IAddressService {

   public List<ProfileAddress> list(Integer profileId);
   
   public boolean add(AddressAddRequest request);
   
   public boolean update(AddressUpdateRequest request);
}
