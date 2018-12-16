package org.particl.rpc.mp.cart;

import java.util.List;

import org.particl.rpc.mp.dto.ShoppingCart;

public interface ICartService {

   public List<ShoppingCart> list(CartListRequest request);
   
   public ShoppingCart get(Integer cartId);
   
   public boolean add(String name, Integer profileId);
   
   public boolean update(Integer cartId, String newCartName);
   
   public boolean remove(Integer cardId);
}
