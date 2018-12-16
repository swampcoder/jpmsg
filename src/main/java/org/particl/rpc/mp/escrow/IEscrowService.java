package org.particl.rpc.mp.escrow;

public interface IEscrowService {

   public boolean add(AddEscrowRequest request);
   
   public boolean update(UpdateEscrowRequest request);
   
   public boolean remove(Integer listingItemTemplateId);
   
   public boolean lock(String orderItemId, String accepted, String memo);
   
   //public boolean 
}
