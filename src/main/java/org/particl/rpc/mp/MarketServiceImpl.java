package org.particl.rpc.mp;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.particl.rpc.mp.dto.Market;
import org.particl.rpc.mp.market.IMarketService;
import org.particl.rpc.mp.market.MarketAddRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;

public class MarketServiceImpl implements IMarketService {

   private final static Type MarketListType = new TypeToken<ArrayList<Market>>() {
   }.getType();

   private final JSONRPC2Session session;

   MarketServiceImpl(JSONRPC2Session session) {
      this.session = session;
   }

   @Override
   public List<Market> list() {

      JSONRPC2Request request = new JSONRPC2Request("market", Arrays.asList("list"), 1);
      JSONRPC2Response response;
      try {
         response = session.send(request);
         Gson gson = new GsonBuilder().setPrettyPrinting().create();
         List<Market> marketList = gson.fromJson(response.getResult().toString(), MarketListType);
         return marketList;
      } catch (JSONRPC2SessionException e) {
         e.printStackTrace();
         return null;
      }

   }

   @Override
   public void add(MarketAddRequest request) {

   }

}
