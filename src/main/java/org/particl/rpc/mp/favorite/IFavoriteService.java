package org.particl.rpc.mp.favorite;

public interface IFavoriteService {

   public boolean list(String profileId, String profileName, Boolean withRelated);
   
   public boolean add(FavoriteAddRequest request);
   
   public boolean remove(FavoriteRemoveRequest request);
}
