package org.particl.rpc.mp;

import org.particl.rpc.mp.payment.IPaymentService;
import org.particl.rpc.mp.payment.PaymentUpdateRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thetransactioncompany.jsonrpc2.JSONRPC2ParseException;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;

public class PaymentServiceImpl implements IPaymentService {

   private JSONRPC2Session session = null;
   public PaymentServiceImpl(JSONRPC2Session session) 
   {
      this.session = session;
   }
   
   
   @Override
   public boolean update(PaymentUpdateRequest request) {

      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String requestJson = gson.toJson(request);
      try {
         JSONRPC2Response response = session.send(JSONRPC2Request.parse(requestJson));
         
      } catch (JSONRPC2SessionException | JSONRPC2ParseException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return false;
   }

}
