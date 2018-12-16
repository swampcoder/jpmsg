package org.particl.rpc.mp.image;

import java.util.List;

import org.particl.rpc.mp.dto.ItemImage;

public interface IImageService {

   public List<ItemImage> list(Integer listingItemTemplateId, Integer listingItemId);
   
   public boolean add(ImageAddRequest request);
   
   public boolean remove(Integer itemImageId);
}
