package org.particl.rpc.mp.market;

import java.util.List;

import org.particl.rpc.mp.dto.Market;

public interface IMarketService {

   public List<Market> list();

   public void add(MarketAddRequest request);
}
