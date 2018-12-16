package org.particl.rpc.mp.category;

import java.util.List;

import org.particl.rpc.mp.dto.ItemCategory;

public interface ICategoryService {

   
   public List<ItemCategory> list();
   
   public ItemCategory get(CategoryGetRequest request);
   
   public boolean add(CategoryAddRequest request);
   
   public boolean update(CategoryUpdateRequest request);
}
