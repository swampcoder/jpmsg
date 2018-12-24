package org.particl.rpc.mp;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.particl.rpc.mp.dto.CurrencyPrice;
import org.particl.rpc.mp.dto.PriceTicker;
import org.particl.rpc.mp.price.IPriceService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;

public class PriceServiceImpl extends MarketBaseRpcService implements IPriceService {

   private final static Type PriceTickerListType = new TypeToken<ArrayList<PriceTicker>>() {}.getType();
   private final static Type CurrencyPriceListType = new TypeToken<ArrayList<CurrencyPrice>>() {}.getType();

   public PriceServiceImpl(JSONRPC2Session session) 
   {
      super(session);
   }
   
   @Override
   public Map<String, PriceTicker> getTickers(String... currencies) {
      
      List<Object> inputs = new ArrayList<Object>();
      for(String c : currencies) 
      {
         inputs.add(c);
      }
      JSONRPC2Request request = new JSONRPC2Request("priceticker", inputs, createId());
      JSONRPC2Response response;
      try {
         response = makeRequest(request);
         Gson gson = new GsonBuilder().setPrettyPrinting().create();
         List<PriceTicker> tickers = gson.fromJson(response.getResult().toString(), PriceTickerListType);
         Map<String, PriceTicker> priceTickerMap = new HashMap<String, PriceTicker>();
         for(PriceTicker pt : tickers) 
         {
            priceTickerMap.put(pt.getCryptoSymbol(), pt);
         }
         return priceTickerMap;
            
      } catch (JSONRPC2SessionException e) {
         e.printStackTrace();
         return null;
      }
   }

   @Override
   public Map<String, CurrencyPrice> currencyPrice(String from, String... to) {

      List<Object> inputs = new ArrayList<Object>();
      inputs.add(from);
      for(String t : to) 
      {
         inputs.add(t);
      }
      
      JSONRPC2Request request = new JSONRPC2Request("currencyprice", inputs, createId());
      JSONRPC2Response response;
      try {
         response = makeRequest(request);
         Gson gson = new GsonBuilder().setPrettyPrinting().create();
         List<CurrencyPrice> currencyPrices = gson.fromJson(response.getResult().toString(), CurrencyPriceListType);
         Map<String, CurrencyPrice> currencyPriceMap = new HashMap<String, CurrencyPrice>();
         for(CurrencyPrice cp : currencyPrices) 
         {
            currencyPriceMap.put(cp.getFrom(), cp);
         }
         return currencyPriceMap;
            
      } catch (JSONRPC2SessionException e) {
         e.printStackTrace();
         return null;
      }
   }

}
