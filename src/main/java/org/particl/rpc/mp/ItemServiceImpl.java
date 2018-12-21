package org.particl.rpc.mp;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.particl.rpc.mp.dto.Item;
import org.particl.rpc.mp.item.IItemService;
import org.particl.rpc.mp.item.ItemGetRequest;
import org.particl.rpc.mp.item.ItemSearchRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;

public class ItemServiceImpl implements IItemService {
   
   private final static Type ItemListType = new TypeToken<ArrayList<Item>>() {}.getType();

   private final JSONRPC2Session session;

   ItemServiceImpl(JSONRPC2Session session) {
      this.session = session;
   }

   @Override
   public List<Item> search(ItemSearchRequest request) {
      
      JSONRPC2Request jsonRequest = new JSONRPC2Request("item", Arrays.asList("search", "*", 9999), 1);
      System.out.println("r1: " + jsonRequest);
      JSONRPC2Response response;
      try {
         response = session.send(jsonRequest);
         System.out.println("response: " + response);
         Gson gson = new GsonBuilder().setPrettyPrinting().create();
         List<Item> itemList = gson.fromJson(response.getResult().toString(), ItemListType);
         for(Item item : itemList)
         {
            System.out.println(item);
         }
         return itemList;
      } catch (JSONRPC2SessionException e) {
         e.printStackTrace();
         return null;
      }
   }


   @Override
   public Item get(ItemGetRequest request) {
      JSONRPC2Request jsonRequest = new JSONRPC2Request("item", Arrays.asList("get", 38), 1);
      JSONRPC2Response response;
      try {
         response = session.send(jsonRequest);
         Gson gson = new GsonBuilder().setPrettyPrinting().create();
         Item item = gson.fromJson(response.getResult().toString(), Item.class);
         System.out.println("Item: " + item);
         return item;
      } catch (JSONRPC2SessionException e) {
         return null;
      }
   }


   @Override
   public boolean flag(String listingItemHash, Integer profileId) {
      JSONRPC2Request jsonRequest = new JSONRPC2Request("item", Arrays.asList("get", 38), 1);
      JSONRPC2Response response;
      try {
         response = session.send(jsonRequest);
         Gson gson = new GsonBuilder().setPrettyPrinting().create();
         Item item = gson.fromJson(response.getResult().toString(), Item.class);
         System.out.println("Item: " + item);
         return true;
      } catch (JSONRPC2SessionException e) {
         return false;
      }
   }
   
   

}
