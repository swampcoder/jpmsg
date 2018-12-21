package org.particl.rpc.mp.market;

import java.util.List;

import org.particl.rpc.mp.MarketException;

import org.particl.rpc.mp.dto.Market;

public interface IMarketService {

   public List<Market> list();

   public void add(String name, String privateKey, String address) throws MarketException;
}
