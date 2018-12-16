package org.particl.rpc.mp.info;

import org.particl.rpc.mp.dto.ItemInformation;

public interface IInformationService {

   public ItemInformation get(Integer listingItemTemplateId);
   
   public boolean update(InformationUpdateRequest request);
}
