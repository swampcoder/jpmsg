package org.particl.rpc.mp;

import org.particl.rpc.mp.dto.ItemInformation;
import org.particl.rpc.mp.info.IInformationService;
import org.particl.rpc.mp.info.InformationGetRequest;
import org.particl.rpc.mp.info.InformationUpdateRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thetransactioncompany.jsonrpc2.JSONRPC2ParseException;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;

public class InformationServiceImpl implements IInformationService {

   private final JSONRPC2Session session;
   public InformationServiceImpl(JSONRPC2Session session) 
   {
      this.session = session;
   }
   
   @Override
   public ItemInformation get(InformationGetRequest request) {
      
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String requestJson = gson.toJson(request);
      try {
         JSONRPC2Response response = session.send(JSONRPC2Request.parse(requestJson));
         String json = response.toJSONString();
         ItemInformation itemInformation = gson.fromJson(json, ItemInformation.class);
         return itemInformation;
      } catch (JSONRPC2SessionException | JSONRPC2ParseException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return null;
   }

   @Override
   public boolean update(InformationUpdateRequest request) {
      // TODO Auto-generated method stub
      return false;
   }

}
