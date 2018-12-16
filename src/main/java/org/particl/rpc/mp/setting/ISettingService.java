package org.particl.rpc.mp.setting;

import java.util.List;

public interface ISettingService {

   public boolean remove(Integer profileId, String key);
   public String get(Integer profileId, String key);
   public List<String> list(Integer profileId);
   public boolean set(Integer profileId, String key, String value);
}
