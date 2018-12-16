package org.particl.rpc.mp.item;

import java.util.List;

import org.particl.rpc.mp.dto.Item;

public interface IItemService {

   
   public List<Item> search(ItemSearchRequest request);
   
   public Item get(ItemGetRequest request);
   
   public boolean flag(String listingItemHash, Integer profileId); 
}
