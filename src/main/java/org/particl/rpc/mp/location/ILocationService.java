package org.particl.rpc.mp.location;

public interface ILocationService {

   public boolean add(LocationAddRequest request);
   public boolean update(LocationUpdateRequest request);
   public boolean remove(Integer listingItemTemplateId);
}
