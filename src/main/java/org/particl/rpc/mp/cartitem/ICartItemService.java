package org.particl.rpc.mp.cartitem;

import java.util.List;

public interface ICartItemService {

   public List list(Integer cardId, Boolean withRelated);
   
   public boolean add(CartItemAddRequest request);
   
   public boolean remove(CartItemRemoveRequest request);
}
