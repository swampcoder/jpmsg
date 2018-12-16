package org.particl.rpc.mp.bid;

import java.util.List;

import org.particl.rpc.mp.dto.Bid;

public interface IBidService {

   public List<Bid> search(BidSearchRequest request);
   
   public boolean bid(Integer bidId);
   
   public boolean cancel(String itemhash, Integer bidId);
   
   public boolean reject(String itemhash, Integer bidId);
   
   public boolean send(BidSendRequest request);
   
}
