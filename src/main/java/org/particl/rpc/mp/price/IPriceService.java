package org.particl.rpc.mp.price;

import java.util.Map;

import org.particl.rpc.mp.dto.CurrencyPrice;
import org.particl.rpc.mp.dto.PriceTicker;

public interface IPriceService {

   
   Map<String, PriceTicker> getTickers(String...currencies); 
   
   Map<String, CurrencyPrice> currencyPrice(String from, String...to);
}
